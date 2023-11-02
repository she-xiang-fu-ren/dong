package cn.iocoder.dong.framework.sms.config;

import cn.iocoder.dong.framework.sms.core.properties.SmsProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 短信配置类
 *
 * @author 芋道源码
 */
@Component
public class YudaoSmsAutoConfiguration {

    private static final YudaoSmsAutoConfiguration yudaoSmsAutoConfiguration  = null;
    private static SmsProperties smsProperties  = null;
    public static SmsProperties getSmsProperties(){
        return smsProperties ;
    }

    @Value("${dong.sms-code.accesskey-id}")
    private String accesskeyId;

    @Value("${dong.sms-code.accesskey-secret}")
    private String accesskeySecret;

    @Value("${dong.sms-code.sign-name}")
    private String signName;

    @Value("${dong.sms-code.template-code}")
    private String templateCode;

    @Bean
    public SmsProperties SmsProperties(){
        SmsProperties smsPropertie = new SmsProperties();
        smsPropertie.setAccesskeyId(accesskeyId);
        smsPropertie.setAccesskeySecret(accesskeySecret);
        smsPropertie.setSignName(signName);
        smsPropertie.setTemplateCode(templateCode);
        smsProperties = smsPropertie;
        return smsPropertie;
    }

}
