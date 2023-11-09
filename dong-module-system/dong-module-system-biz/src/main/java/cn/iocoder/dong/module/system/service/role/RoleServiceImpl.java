package cn.iocoder.dong.module.system.service.role;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysRoleDO;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysUserRoleDO;
import cn.iocoder.dong.module.system.dal.mysql.role.RoleMapper;
import cn.iocoder.dong.module.system.dal.mysql.role.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {


    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 根据用户id获取角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRoleDO> getRoleByUserId(Long userId) {
        if (isRoleAndAdmin(userId)){
            //获取所有权限
            return roleMapper.selectListAll();
        }else {
            return roleMapper.selectListByUserId(userId);
        }

    }

    /**
     * 判断是否是管理员
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean isRoleAndAdmin(Long userId) {
        int count = roleMapper.isRoleAndAdmin(userId);
        return count>0;
    }
}
