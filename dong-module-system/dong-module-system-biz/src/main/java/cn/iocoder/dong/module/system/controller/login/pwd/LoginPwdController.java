package cn.iocoder.dong.module.system.controller.login.pwd;

import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.module.system.controller.login.pwd.vo.LoginPwdVO;
import cn.iocoder.dong.module.system.service.login.pwd.LoginPwdService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static cn.iocoder.dong.framework.common.pojo.CommonResult.error;
import static cn.iocoder.dong.framework.common.pojo.CommonResult.success;
import static cn.iocoder.dong.module.system.ErrorCodeConstants.AUTH_LOGIN_FAILED_MIXED;

/**
 * 基于账号密码登录
 */
@RestController
@RequestMapping("/system/pwd")
public class LoginPwdController {

    @Resource
    private LoginPwdService loginPwdService;

    @PostMapping("/login")
    @PermitAll
    public CommonResult<String> PwdLogin(@RequestBody LoginPwdVO loginPwdVO,
                                 HttpServletResponse response){
        String token  = loginPwdService.reginter(loginPwdVO);
        if (token != null){
//            Cookie cookie = new Cookie("Admin-Token",token);
//            cookie.setPath("/");
//            response.addCookie(cookie);
            return success(token);
        }else {
            return error(AUTH_LOGIN_FAILED_MIXED);
        }
    }

}
