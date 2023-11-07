package cn.iocoder.dong.module.system.controller.login.sms;

import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.module.system.controller.login.sms.vo.LoginSmsVO;
import cn.iocoder.dong.module.system.service.login.sms.LoginSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.dong.framework.common.pojo.CommonResult.success;

/**
 * 基于手机号短信登录
 */
@RestController
@RequestMapping("/system/sms")
public class LoginSmsController {

    @Autowired
    private LoginSmsService loginSmsService;
    /**
     * 获取验证码
     * @param phone 手机号
     * @return
     */
    @GetMapping("/get/code")
    public CommonResult<String> getSmsCode(@RequestParam("phone") String phone){
        return success(loginSmsService.getSmsCode(phone));
    }

    @PostMapping("login")
    public CommonResult<String> login(@RequestBody LoginSmsVO loginSmsVO){
        return success(loginSmsService.login(loginSmsVO));
    }
}
