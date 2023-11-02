package cn.iocoder.dong.framework.sms.core.client;

import cn.iocoder.dong.framework.common.core.KeyValue;
import cn.iocoder.dong.framework.sms.core.client.dto.SmsSendRespDTO;

import java.util.List;

public interface SmsClient {
    /**
     * 发送消息
     *
     * @param mobile 手机号
     * @param templateParams 短信模板参数。通过 List 数组，保证参数的顺序
     * @return 短信发送结果
     */
    SmsCommonResult<SmsSendRespDTO> sendSms(String mobile,
                                            List<KeyValue<String, Object>> templateParams);
}
