package cn.iocoder.dong.module.system.controller.login.sms.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginSmsVO {

    private String phone;

    private String code;
}
