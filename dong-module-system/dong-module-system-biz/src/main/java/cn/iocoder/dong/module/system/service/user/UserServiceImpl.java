package cn.iocoder.dong.module.system.service.user;

import cn.iocoder.dong.module.system.dal.user.entity.UserDO;
import cn.iocoder.dong.module.system.dal.user.mapper.UserMapper;
import cn.iocoder.dong.module.system.service.help.UserPwdEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPwdEncoder userPwdEncoder;

    @Override
    public UserDO selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return userPwdEncoder.match(rawPassword, encodedPassword);
    }

    @Override
    public UserDO createUser(String username, String password) {
        UserDO userDO = new UserDO();
        userDO.setUserName(username);
        userDO.setUserPassword(password);
        userDO.setDeleted(0);
        userDO.setStatus(0);
        userDO.setLoginType(0);
        userMapper.insert(userDO);
        return userDO;
    }
}
