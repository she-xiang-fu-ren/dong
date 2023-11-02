package cn.iocoder.dong.module.system.controller.login.pwd;

import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.module.system.service.login.pwd.LoginPwdService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public CommonResult<String> PwdLogin(@RequestParam(name = "username") String username,
                                 @RequestParam(name = "password") String password,
                                 HttpServletResponse response){
        String s  = loginPwdService.reginter(username,password);
        if (s!=null){
            Cookie cookie = new Cookie("dong",s);
            response.addCookie(cookie);
            return success(s);
        }else {
            return error(AUTH_LOGIN_FAILED_MIXED);
        }
    }

}
