package cn.iocoder.dong.module.system.dal.mysql.user;

import cn.iocoder.dong.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapperX<UserDO> {
    default UserDO selectByUserName(String username){
        return selectOne(UserDO::getUserName,username);
    }
}