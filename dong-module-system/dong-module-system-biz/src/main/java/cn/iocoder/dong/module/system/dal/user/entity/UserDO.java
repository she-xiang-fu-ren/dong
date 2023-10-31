package cn.iocoder.dong.module.system.dal.user.entity;

import cn.iocoder.dong.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "system_users", autoResultMap = true) // 由于 SQL Server 的 system_user 是关键字，所以使用 system_users
@Data
public class UserDO extends BaseDO {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 第三方登录id
     */
    private String thirdAccountId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 登录方式
     */
    private Integer loginType;

    /**
     * 登录的状态
     */

    private Integer status;

    /**
     * 删除标记
     */
    private Integer deleted;
}
