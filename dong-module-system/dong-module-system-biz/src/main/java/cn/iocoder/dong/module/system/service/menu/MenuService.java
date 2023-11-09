package cn.iocoder.dong.module.system.service.menu;

import cn.iocoder.dong.module.system.controller.login.vo.RouterVO;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysMenuDO;

import java.util.List;

/**
 * 菜单
 */
public interface MenuService {
    /**
     * 根据用户id获取拥有的权限
     * @param userId
     * @return
     */
    List<SysMenuDO> getMenuByUserId(Long userId);

    /**
     *构建前端路由所需要的菜单
     * @param menuByUserId
     * @return
     */
    List<RouterVO> buildMenus(List<SysMenuDO> menuByUserId);

}
