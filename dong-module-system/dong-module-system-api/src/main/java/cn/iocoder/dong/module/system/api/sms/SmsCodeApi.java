package cn.iocoder.dong.module.system.api.sms;

import java.util.HashMap;

public interface SmsCodeApi {
    /**
     * 发送短信
     * @param phone 手机号
     * @param code 验证码
     */
    void sendSms(String phone, HashMap<String, Object> code);
}
