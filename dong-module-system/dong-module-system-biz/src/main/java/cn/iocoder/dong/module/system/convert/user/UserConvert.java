package cn.iocoder.dong.module.system.convert.user;

import cn.iocoder.dong.module.system.controller.user.vo.UserInfoVO;
import cn.iocoder.dong.module.system.controller.user.vo.UserVO;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserInfoDO convert(UserInfoVO userInfoVO);

    UserDO convert(UserVO userVO);

}
