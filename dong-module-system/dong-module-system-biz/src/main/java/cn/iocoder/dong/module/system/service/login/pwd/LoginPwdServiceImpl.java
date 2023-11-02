package cn.iocoder.dong.module.system.service.login.pwd;

import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import cn.iocoder.dong.module.system.service.help.UserSessionHelper;
import cn.iocoder.dong.module.system.service.user.UserService;
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


    @Override
    public String reginter(String username, String password) {
        //查询是否存在当前账号
        UserDO userDO =userService.selectByUserName(username);
        if (userDO == null){
            //说明不存在当前账号
            //记录日志
            throw exception(AUTH_LOGIN_NOT_USER_DISABLED);
        }

        //校验密码
        if (!userService.isPasswordMatch(password,userDO.getUserPassword())){
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        return userSessionHelper.genSession(userDO.getId());
    }
}
