package cn.iocoder.dong.module.system.service.login.pwd;

import cn.iocoder.dong.module.system.controller.login.pwd.vo.LoginPwdVO;

public interface LoginPwdService {
    /**
     * 校验，获取token
     * @param loginPwdVO
     * @return
     */
    String reginter(LoginPwdVO loginPwdVO);

    /**
     * 退出登录
     * @param token
     */
    void logout(String token);

}
