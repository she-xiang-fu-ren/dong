package cn.iocoder.dong.module.system.dal.mysql.user;

import cn.iocoder.dong.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapperX<SysUserDO> {
    default SysUserDO selectByUserName(String username){
        return selectOne(SysUserDO::getUserName,username);
    }
}
