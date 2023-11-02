package cn.iocoder.dong.module.system.controller.user.vo;

import lombok.Data;

@Data
public class UserVO {

    /**
     * 第三方id
     */
    private String thirdAccountId;

    /**
     * 用户名（账号）
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 登录方式
     */
    private Integer loginType;
}
