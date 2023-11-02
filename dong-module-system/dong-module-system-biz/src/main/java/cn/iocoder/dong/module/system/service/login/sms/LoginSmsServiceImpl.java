package cn.iocoder.dong.module.system.service.login.sms;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.iocoder.dong.framework.sms.core.client.Impl.aliyun.AliyunSmsClient;
import cn.iocoder.dong.framework.sms.core.client.SmsClient;
import cn.iocoder.dong.module.system.controller.login.sms.vo.LoginSmsVO;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserInfoDO;
import cn.iocoder.dong.module.system.dal.mysql.user.UserInfoMapper;
import cn.iocoder.dong.module.system.service.help.UserSessionHelper;
import cn.iocoder.dong.module.system.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static cn.iocoder.dong.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.dong.module.system.ErrorCodeConstants.*;

@Service
public class LoginSmsServiceImpl implements LoginSmsService{

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserService userService;

    @Resource
    private UserSessionHelper userSessionHelper;

    private final String sms = "sms:";

    @Override
    public String getSmsCode(String phone) {
        String key = sms+phone;
        //判断缓存中是否存在该手机号，如果存在说明已经发送过
        if (stringRedisTemplate.hasKey(key)){
            throw exception(SMS_CODE_SEND_TOO_FAST);
        }
        //TODO:目前来说支持阿里云短信、可扩展。一个短信服务就是一个实现类。只要还是SmsClient接口
        SmsClient smsClient = new AliyunSmsClient();
        //获取验证码
        String code = createCode();
        smsClient.sendSms(phone, MapUtil.of("code",code));
        stringRedisTemplate.opsForValue().set(key,code,5, TimeUnit.MINUTES);
        return "发送成功！";
    }

    /**
     * 使用手机号短信登录
     *
     * @return
     */
    @Override
    public String login(LoginSmsVO loginSmsVO) {
        //校验验证码是否正确
        String codeCa = stringRedisTemplate.opsForValue().get(sms + loginSmsVO.getPhone());
        //有俩种情况缓存中验证码为空，一种是没有获取验证码直接登录，一种是在缓存中验证码过期了。第一种就暂时不考虑。写的demo
        if (codeCa==null){
            throw exception(SMS_CODE_EXPIRED);
        }
        //判断验证码是否正确
        if (!loginSmsVO.getCode().equals(codeCa)){
            throw exception(SMS_CODE_NOT_CORRECT);
        }
        //查询用户是否存在
        UserInfoDO userInfoDO = userInfoMapper.selectByUserPhone(loginSmsVO.getPhone());
        if (userInfoDO == null){
            //说明用户第一次登录，需要创建账号
        }
        assert userInfoDO != null;
        UserDO userDo =userService.findById(userInfoDO.getUserId());
        //判断用户是否被禁用
        if (userDo.getDeleted().equals(1)||userDo.getStatus().equals(1)){
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return userSessionHelper.genSession(userDo.getId());
    }

    private String createCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}
