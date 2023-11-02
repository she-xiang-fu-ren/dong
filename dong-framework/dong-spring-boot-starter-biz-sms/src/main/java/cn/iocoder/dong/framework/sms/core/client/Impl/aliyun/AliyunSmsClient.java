package cn.iocoder.dong.framework.sms.core.client.Impl.aliyun;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.dong.framework.common.core.KeyValue;
import cn.iocoder.dong.framework.sms.config.YudaoSmsAutoConfiguration;
import cn.iocoder.dong.framework.sms.core.client.Impl.AbstractSmsClient;
import cn.iocoder.dong.framework.sms.core.client.SmsCommonResult;
import cn.iocoder.dong.framework.sms.core.client.dto.SmsSendRespDTO;
import cn.iocoder.dong.framework.sms.core.properties.SmsProperties;
import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.common.annotations.VisibleForTesting;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

public class AliyunSmsClient extends AbstractSmsClient {

    private volatile IAcsClient smsClient;

    private final SmsProperties smsProperties = YudaoSmsAutoConfiguration.getSmsProperties();
    
    

    @Override
    protected SmsCommonResult<SmsSendRespDTO> doSendSms(String mobile, List<KeyValue<String, Object>> templateParams) throws Throwable {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setPhoneNumbers(mobile);
        request.setSignName(smsProperties.getSignName());
        request.setTemplateCode(smsProperties.getTemplateCode());
//        request.setTemplateParam(JsonUtils.toJsonString(MapUtils.convertMap(templateParams)));
        String msgCode = getMsgCode();
        request.setTemplateParam("{\"code\":\"" + msgCode + "\"}");
        return invoke(request, response ->{
            SmsSendRespDTO smsSendRespDTO = new SmsSendRespDTO();
            smsSendRespDTO.setSerialNo(response.getBizId());
            return smsSendRespDTO;
        });
    }

    @Override
    protected void doInit() throws Throwable {
        IClientProfile profile = DefaultProfile.getProfile(ENDPOINT, smsProperties.getAccesskeyId(), smsProperties.getAccesskeySecret());
        smsClient = new DefaultAcsClient(profile);
    }

    @VisibleForTesting
    <T extends AcsResponse, R> SmsCommonResult<R> invoke(AcsRequest<T> request, Function<T, R> responseConsumer) {
        try {
            // 执行发送. 由于阿里云 sms 短信没有统一的 Response，但是有统一的 code、message、requestId 属性，所以只好反射
            T sendResult = smsClient.getAcsResponse(request);
            String code = (String) ReflectUtil.getFieldValue(sendResult, "code");
            String message = (String) ReflectUtil.getFieldValue(sendResult, "message");
            String requestId = (String) ReflectUtil.getFieldValue(sendResult, "requestId");
            // 解析结果
            R data = null;
            if (Objects.equals(code, "OK")) { // 请求成功的情况下
                data = responseConsumer.apply(sendResult);
            }
            // 拼接结果
            return SmsCommonResult.build(code, message, requestId, data, codeMapping);
        } catch (ClientException ex) {
            return SmsCommonResult.build(ex.getErrCode(), formatResultMsg(ex), ex.getRequestId(), null, codeMapping);
        }
    }
    private static String formatResultMsg(ClientException ex) {
        if (StrUtil.isEmpty(ex.getErrorDescription())) {
            return ex.getErrMsg();
        }
        return ex.getErrMsg() + " => " + ex.getErrorDescription();
    }

    /**
     * 生成随机的6位数，短信验证码
     * @return
     */
    private static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }

}
