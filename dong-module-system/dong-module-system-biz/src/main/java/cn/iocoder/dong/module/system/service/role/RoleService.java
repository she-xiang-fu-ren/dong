package cn.iocoder.dong.module.system.service.role;

import cn.iocoder.dong.module.system.dal.dataobject.entity.SysRoleDO;

import java.util.List;

public interface RoleService {
    /**
     * 根据用户id获取角色
     * @param userId
     * @return
     */
    List<SysRoleDO> getRoleByUserId(Long userId);

    /**
     * 判断是否是管理员
     * @param userId
     * @return
     */
    Boolean isRoleAndAdmin(Long userId);
}
