package cn.iocoder.dong.framework.sms.core.properties;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Duration;

@Data
public class SmsProperties {
    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空")
    private Duration expireTimes;
    /**
     * 短信发送频率
     */
    @NotNull(message = "短信发送频率不能为空")
    private Duration sendFrequency;
    /**
     * 每日发送最大数量
     */
    @NotNull(message = "每日发送最大数量不能为空")
    private Integer sendMaximumQuantityPerDay;
    /**
     * 验证码最小值
     */
    @NotNull(message = "验证码最小值不能为空")
    private Integer beginCode;
    /**
     * 验证码最大值
     */
    @NotNull(message = "验证码最大值不能为空")
    private Integer endCode;

    /**
     * 短信签名
     */
    @NotNull(message = "短信签名不能为空")
    private String signName;

    /**
     * accessKeyId
     */
    @NotNull(message = "accessKeyId不能为空")
    private String accesskeyId;

    /**
     * accessKeySecret
     */
    @NotNull(message = "accessKeySecret不能为空")
    private String accesskeySecret;

    /**
     * 短信模板
     */
    @NotNull(message = "短信模板不能为空")
    private String templateCode;
}
