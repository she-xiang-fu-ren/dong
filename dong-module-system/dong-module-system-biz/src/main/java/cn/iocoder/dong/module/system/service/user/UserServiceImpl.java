package cn.iocoder.dong.module.system.service.user;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import cn.iocoder.dong.module.system.service.help.UserPwdEncoder;
import cn.iocoder.dong.module.system.dal.mysql.user.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserPwdEncoder userPwdEncoder;

    @Override
    public UserDO selectByUserName(String username) {
        UserDO userDO = userMapper.selectByUserName(username);
        userDO.setStatus(1);
        userMapper.updateById(userDO);
        return userDO;
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return userPwdEncoder.match(rawPassword, encodedPassword);
    }

    @Override
    public UserDO createUser(String username, String password) {
        UserDO userDO = new UserDO();
        userDO.setUserName(username);
        userDO.setUserPassword(userPwdEncoder.encPwd(password));
        userDO.setThirdAccountId("100");
        userDO.setDeleted(0);
        userDO.setStatus(0);
        userDO.setLoginType(0);
        userMapper.insert(userDO);
        return userDO;
    }
}
