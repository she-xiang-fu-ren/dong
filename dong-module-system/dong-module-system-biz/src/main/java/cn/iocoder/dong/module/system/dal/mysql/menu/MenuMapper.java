package cn.iocoder.dong.module.system.dal.mysql.menu;

import cn.iocoder.dong.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysMenuDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Arrays;
import java.util.List;

@Mapper
public interface  MenuMapper extends BaseMapperX<SysMenuDO> {

    default List<SysMenuDO> selectAll(){
        return selectList(new LambdaQueryWrapper<SysMenuDO>().eq(SysMenuDO::getStatus,0).in(SysMenuDO::getMenuType, Arrays.asList("M","C")));
    };

    List<SysMenuDO> selectMenuByUserId(@Param("userId") Long userId);
}
