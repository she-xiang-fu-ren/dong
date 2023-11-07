package cn.iocoder.dong.module.system.service.login.pwd;

import cn.iocoder.dong.module.system.controller.login.pwd.vo.LoginPwdVO;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import cn.iocoder.dong.module.system.dal.redis.RedisKeyConstants;
import cn.iocoder.dong.module.system.framework.captcha.CaptchaCodeProperties;
import cn.iocoder.dong.module.system.service.help.UserSessionHelper;
import cn.iocoder.dong.module.system.service.user.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static cn.iocoder.dong.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.dong.module.system.ErrorCodeConstants.*;

@Service
public class LoginPwdServiceImpl implements LoginPwdService{

    @Resource
    private UserService userService;

    @Resource
    private UserSessionHelper userSessionHelper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String reginter(LoginPwdVO loginPwdVO) {
        //查询是否存在当前账号
        UserDO userDO =userService.selectByUserName(loginPwdVO.getUsername());
        if (userDO == null){
            //说明不存在当前账号
            //记录日志
            throw exception(AUTH_LOGIN_NOT_USER_DISABLED);
        }
        //查询是否开启验证码
        if ("true".equals(CaptchaCodeProperties.getCaptchaEnabled())){
            String s = stringRedisTemplate.opsForValue().get(RedisKeyConstants.CAPTCHA_CODE+loginPwdVO.getUuid());
            if (loginPwdVO.getCode()!=null&&s==null){
                throw exception(SMS_CODE_EXPIRED);
            }
            //校验验证码是否正确
            if (!s.equals(loginPwdVO.getCode())){
                throw exception(SMS_CODE_NOT_CORRECT);
            }
        }

        //校验密码
        if (!userService.isPasswordMatch(loginPwdVO.getPassword(),userDO.getUserPassword())){
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        return userSessionHelper.genSession(userDO.getId());
    }
}
