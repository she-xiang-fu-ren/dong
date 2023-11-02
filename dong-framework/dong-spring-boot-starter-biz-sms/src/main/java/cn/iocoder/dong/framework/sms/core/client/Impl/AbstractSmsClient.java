package cn.iocoder.dong.framework.sms.core.client.Impl;

import cn.iocoder.dong.framework.sms.core.client.Impl.aliyun.AliyunSmsCodeMapping;
import cn.iocoder.dong.framework.sms.core.client.SmsClient;
import cn.iocoder.dong.framework.sms.core.client.SmsCodeMapping;
import cn.iocoder.dong.framework.sms.core.client.SmsCommonResult;
import cn.iocoder.dong.framework.sms.core.client.dto.SmsSendRespDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class AbstractSmsClient implements SmsClient {

    protected final SmsCodeMapping codeMapping = new AliyunSmsCodeMapping();

    /**
     * REGION, 使用杭州
     */
    protected static final String ENDPOINT = "cn-hangzhou";


    /**
     * 发送消息
     *
     * @param mobile         手机号
     * @param templateParams 短信模板参数。通过 List 数组，保证参数的顺序(暂时没用上)
     * @return 短信发送结果
     */
    @Override
    public final SmsCommonResult<SmsSendRespDTO> sendSms(String mobile, Map<String, Object> templateParams) {

        // 执行短信发送
        SmsCommonResult<SmsSendRespDTO> result;
        try {
            doInit();
            result = doSendSms(mobile, templateParams);
        } catch (Throwable ex) {
            // 打印异常日志
            log.error("[sendSms][发送短信异常，sendLogId({}) mobile({}) apiTemplateId({}) templateParams({})]",
                     mobile, templateParams, ex);
            // 封装返回
            return SmsCommonResult.error(ex);
        }
        return result;
    }

    protected abstract SmsCommonResult<SmsSendRespDTO> doSendSms(String mobile, Map<String, Object> templateParams)
            throws Throwable;

    protected abstract void doInit() throws Throwable;

}
