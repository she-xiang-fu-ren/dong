package cn.iocoder.dong.module.system.service.user;

import cn.iocoder.dong.module.system.controller.user.vo.UserVO;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysUserDO;

public interface UserService {
    /**
     * 根据账号名查账号
     * @param username
     * @return
     */
    SysUserDO selectByUserName(String username);

    /**
     * 判断密码是否匹配
     *
     * @param rawPassword 未加密的密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    boolean isPasswordMatch(String rawPassword, String encodedPassword);

    /**
     * 创建账号
     * @return 返回
     */
    String createUser(UserVO userVO);

    /**
     * 根据之间id查询用户
     * @param userId
     * @return
     */
    SysUserDO findById(Long userId);

    /**
     * 根据手机号获取用户
     * @param phone
     * @return
     */
    SysUserDO getPhone(String phone);

//    /**
//     * 创建用户详情
//     * @param userInfoVO
//     * @return
//     */
//    String createUserInfo(UserInfoVO userInfoVO);
}
