package cn.iocoder.dong.module.system.dal.dataobject.user;

import cn.iocoder.dong.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@TableName(value = "system_users_info", autoResultMap = true) // 由于 SQL Server 的 system_user 是关键字，所以使用 system_users
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDO extends BaseDO {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 最后登录的时间
     */

    private Date loginDate;

    /**
     * ip
     */
    private String Ip;

}
