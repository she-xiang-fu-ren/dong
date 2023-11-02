package cn.iocoder.dong.module.system.dal.mysql.user;

import cn.iocoder.dong.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapperX<UserInfoDO> {
    default UserInfoDO selectByUserPhone(String userPhone){
        return selectOne(UserInfoDO::getPhone,userPhone);
    }
}
