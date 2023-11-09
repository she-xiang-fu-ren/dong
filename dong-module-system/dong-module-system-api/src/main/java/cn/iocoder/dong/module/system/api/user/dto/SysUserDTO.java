package cn.iocoder.dong.module.system.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDTO {

    private static final long serialVersionUID = 157177156283090798L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户类型（00系统用户）
     */
    private String userType;

    /**
     * 手机号码
     */
    private String phonenumber;

}

