package cn.iocoder.dong.module.system.service.user;
import cn.iocoder.dong.module.system.controller.user.vo.UserInfoVO;
import cn.iocoder.dong.module.system.controller.user.vo.UserVO;
import cn.iocoder.dong.module.system.convert.user.UserConvert;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserInfoDO;
import cn.iocoder.dong.module.system.service.help.UserPwdEncoder;
import cn.iocoder.dong.module.system.dal.mysql.user.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserPwdEncoder userPwdEncoder;

    @Resource
    private UserInfoMapper userInfoMapper;

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
    public String createUser(UserVO userVO) {
        UserDO userDO = UserConvert.INSTANCE.convert(userVO);
        userDO.setUserPassword(userPwdEncoder.encPwd(userVO.getUserPassword()));
        userDO.setDeleted(0);
        userDO.setStatus(0);
        userMapper.insert(userDO);
        return "创建成功";
    }

    /**
     * 创建用户详情
     *
     * @param userInfoVO
     * @return
     */
    @Override
    public String createUserInfo(UserInfoVO userInfoVO) {
        UserInfoDO userInfoDO = UserConvert.INSTANCE.convert(userInfoVO);
        userInfoDO.setLoginDate(new Date());
        userInfoMapper.insert(userInfoDO);
        return "创建成功!";
    }

    /**
     * 根据之间id查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public UserDO findById(Long userId) {
        return userMapper.selectOne(UserDO::getId, userId);
    }
}
