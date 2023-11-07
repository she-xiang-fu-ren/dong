package cn.iocoder.dong.module.system.controller.captcha;


import cn.iocoder.dong.framework.common.base.Base64;
import cn.iocoder.dong.module.system.dal.redis.RedisKeyConstants;
import cn.iocoder.dong.module.system.framework.captcha.CaptchaCodeProperties;
import com.google.code.kaptcha.Producer;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Tag(name = "获取验证码 - 认证")
@RestController
@RequestMapping()
@Validated
@Slf4j
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/captchaImage")
    public Map<String,Object> createInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("msg","操作成功");
        map.put("code",200);
        //判断验证码是否开启
        if (!"true".equals(CaptchaCodeProperties.getCaptchaEnabled())){
            return map;
        }
        map.put("captchaEnabled", true);
        //保存验证码信息
        String key = UUID.randomUUID().toString();
        String captKey = RedisKeyConstants.CAPTCHA_CODE +key;

        String capStr = null, code = null;
        BufferedImage image = null;

        //判断验证码是什么类型的
        String captchaType = CaptchaCodeProperties.getCaptchaType();
        if ("math".equals(captchaType)){
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@")+1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if ("char".equals(captchaType)){
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }
        //存缓存中
        assert code != null;
        stringRedisTemplate.opsForValue().set(captKey,code,2, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
        map.put("uuid", key);
        map.put("img", Base64.encode(os.toByteArray()));
        return map;

    }
}
