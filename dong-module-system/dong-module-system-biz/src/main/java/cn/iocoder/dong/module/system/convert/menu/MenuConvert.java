package cn.iocoder.dong.module.system.convert.menu;

import cn.iocoder.dong.module.system.controller.login.vo.SysMenuVO;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysMenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    default List<SysMenuVO> convert(List<SysMenuDO> menusDo){
        List<SysMenuVO> sysMenuVOS = new ArrayList<>();
        for (SysMenuDO sysMenuDO : menusDo) {
            sysMenuVOS.add(convert(sysMenuDO));
        }
        return sysMenuVOS;
    };

    SysMenuVO convert(SysMenuDO menuDO);
}
