package cn.iocoder.dong.module.system.api.sms;

import cn.iocoder.dong.framework.sms.core.client.Impl.aliyun.AliyunSmsClient;
import cn.iocoder.dong.framework.sms.core.client.SmsClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SmsCodeApiImpl implements SmsCodeApi {
    /**
     * 发送短信
     *
     * @param phone 手机号
     * @param code  验证码
     */
    @Override
    public void sendSms(String phone, HashMap<String, Object> code) {
        //TODO:目前来说支持阿里云短信、可扩展。一个短信服务就是一个实现类。只要还是SmsClient接口
        SmsClient smsClient = new AliyunSmsClient();
        smsClient.sendSms(phone,code);
    }
}
