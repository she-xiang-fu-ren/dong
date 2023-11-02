package cn.iocoder.dong.module.system.controller.user.vo;

import lombok.Data;

@Data
public class UserInfoVO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 头像地址
     */
    private String avatar;
}
