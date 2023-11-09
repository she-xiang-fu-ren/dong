package cn.iocoder.dong.module.system.convert.user;

import cn.iocoder.dong.module.system.api.user.dto.SysUserDTO;
import cn.iocoder.dong.module.system.controller.user.vo.UserVO;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);


    SysUserDO convert(UserVO userVO);

    SysUserDTO convert(SysUserDO userDO);
}
