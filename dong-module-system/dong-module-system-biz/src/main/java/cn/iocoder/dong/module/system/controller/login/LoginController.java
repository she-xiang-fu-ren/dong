package cn.iocoder.dong.module.system.controller.login;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.framework.security.config.SecurityProperties;
import cn.iocoder.dong.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.dong.module.system.api.user.dto.SysUserDTO;
import cn.iocoder.dong.module.system.controller.login.vo.RouterVO;
import cn.iocoder.dong.module.system.dal.dataobject.entity.SysMenuDO;
import cn.iocoder.dong.module.system.service.login.pwd.LoginPwdService;
import cn.iocoder.dong.module.system.service.menu.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

import static cn.iocoder.dong.framework.common.pojo.CommonResult.success;
import static cn.iocoder.dong.framework.security.core.util.SecurityFrameworkUtils.obtainAuthorization;

@RestController
public class LoginController {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private LoginPwdService loginPwdService;

    @Resource
    private MenuService menuService;

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public Map<String,Object> getInfo() {
        Map<String, Object> map = new HashMap<>();

        SysUserDTO loginUser = SecurityFrameworkUtils.getLoginUser();
        // 角色集合--->暂时写死admin。后面扩展
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        // 权限集合--->暂时写死admin。后面扩展
        Set<String> permissions = new HashSet<>();
        permissions.add("*:*:*");
        map.put("user", loginUser);
        map.put("roles", roles);
        map.put("permissions", permissions);
        return map;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public CommonResult<List<RouterVO>> getRouters() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        List<SysMenuDO> menuByUserId = menuService.getMenuByUserId(userId);
        List <RouterVO> menus =menuService.buildMenus(menuByUserId);
        return success(menus);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @PostMapping("/logout")
    @PermitAll
    @Operation(summary = "登出系统")
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            loginPwdService.logout(token);
        }
        return success(true);
    }
}
