package cn.iocoder.dong.module.system.service.login.sms;

import cn.iocoder.dong.framework.sms.core.client.Impl.aliyun.AliyunSmsClient;
import cn.iocoder.dong.framework.sms.core.client.Impl.aliyun.AliyunSmsCodeMapping;
import cn.iocoder.dong.framework.sms.core.client.SmsClient;
import org.springframework.stereotype.Service;

@Service
public class LoginSmsServiceImpl implements LoginSmsService{


    @Override
    public String getSmsCode(String phone) {
        SmsClient smsClient = new AliyunSmsClient();
        smsClient.sendSms(phone,null);
        return "发送成功！";
    }
}
