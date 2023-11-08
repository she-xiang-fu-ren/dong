package cn.iocoder.dong.module.system.service.user;

import cn.iocoder.dong.module.system.controller.user.vo.UserVO;
import cn.iocoder.dong.module.system.convert.user.UserConvert;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysUserDO;
import cn.iocoder.dong.module.system.dal.mysql.user.UserMapper;
import cn.iocoder.dong.module.system.service.help.UserPwdEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserPwdEncoder userPwdEncoder;



    @Override
    public SysUserDO selectByUserName(String username) {
        SysUserDO userDO = userMapper.selectByUserName(username);
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
        SysUserDO userDO = UserConvert.INSTANCE.convert(userVO);
        userDO.setPassword(userPwdEncoder.encPwd(userVO.getUserPassword()));
        userDO.setDelFlag(0);
        userDO.setStatus(0);
        userMapper.insert(userDO);
        return "创建成功";
    }

//    /**
//     * 创建用户详情
//     *
//     * @param userInfoVO
//     * @return
//     */
//    @Override
//    public String createUserInfo(UserInfoVO userInfoVO) {
//        UserInfoDO userInfoDO = UserConvert.INSTANCE.convert(userInfoVO);
//        userInfoDO.setLoginDate(new Date());
//        userInfoMapper.insert(userInfoDO);
//        return "创建成功!";
//    }

    /**
     * 根据之间id查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public SysUserDO findById(Long userId) {
        return userMapper.selectOne(SysUserDO::getUserId, userId);
    }

    @Override
    public SysUserDO getPhone(String phone) {
        return userMapper.selectOne(SysUserDO::getPhonenumber,phone);
    }
}
