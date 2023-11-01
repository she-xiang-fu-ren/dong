package cn.iocoder.dong.module.system.service.user;

import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;

public interface UserService {
    /**
     * 根据账号名查账号
     * @param username
     * @return
     */
    UserDO selectByUserName(String username);

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
     * @param username 用户名
     * @param password 密码
     * @return 返回
     */
    UserDO createUser(String username, String password);
}
