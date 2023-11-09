package cn.iocoder.dong.module.system.dal.mysql.role;

import cn.iocoder.dong.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysRoleDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapperX<SysRoleDO> {

    default SysRoleDO getRoleInRoleId(List<Long> collect){
        return selectOne(new LambdaQueryWrapper<SysRoleDO>()
        .in(SysRoleDO::getRoleId,collect)
        .eq(SysRoleDO::getRoleKey,"admin"));
    };

    default List<SysRoleDO> selectListAll(){
        return selectList();
    };

    int isRoleAndAdmin(@Param("userId") Long userId);

    List<SysRoleDO> selectListByUserId(@Param("userId")Long userId);
}
