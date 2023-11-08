package cn.iocoder.dong.module.system.api.user;

import cn.iocoder.dong.module.system.api.user.dto.SysUserDTO;

public interface LoginUserApi {
    /**
     * 根据token获取登录人信息
     * @param token
     * @return
     */
    SysUserDTO getLoginUserAndToken(String token);
}
