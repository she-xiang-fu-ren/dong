package cn.iocoder.dong.module.system.framework.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "dong.sys-account")
@Validated
@Component
public class CaptchaCodeProperties {

    private static String captchaEnabled;

    private static String captchaType;

    public static String getCaptchaEnabled() {
        return captchaEnabled;
    }

    public void setCaptchaEnabled(String captchaEnabled) {
        CaptchaCodeProperties.captchaEnabled = captchaEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        CaptchaCodeProperties.captchaType = captchaType;
    }
}
