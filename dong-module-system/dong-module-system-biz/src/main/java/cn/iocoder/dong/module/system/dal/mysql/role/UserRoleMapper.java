package cn.iocoder.dong.module.system.dal.mysql.role;

import cn.iocoder.dong.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysUserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapperX<SysUserRoleDO> {
    /**
     * 根据用户id获取用户的角色
     * @param userId
     * @return
     */
    default List<SysUserRoleDO> getRoleByUserId(Long userId){
        return selectList(SysUserRoleDO::getUserId,userId);
    };
}
