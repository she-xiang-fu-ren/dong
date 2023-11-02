package cn.iocoder.dong.module.system.service.login.sms;

import cn.iocoder.dong.module.system.controller.login.sms.vo.LoginSmsVO;

public interface LoginSmsService {
    /**
     * 获取验证码
     * @param phone 手机号
     * @return
     */
    String getSmsCode(String phone);

    /**
     * 使用手机号短信登录
     * @return
     */
    String login(LoginSmsVO loginSmsVO);
}
