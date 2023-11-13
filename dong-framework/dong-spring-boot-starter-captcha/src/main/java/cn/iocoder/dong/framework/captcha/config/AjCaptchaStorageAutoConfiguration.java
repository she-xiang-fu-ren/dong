package cn.iocoder.dong.framework.captcha.config;


import cn.iocoder.dong.framework.captcha.core.service.CaptchaCacheService;
import cn.iocoder.dong.framework.captcha.core.service.impl.CaptchaServiceFactory;
import cn.iocoder.dong.framework.captcha.core.service.impl.RedisCaptchaServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * 存储策略自动配置.
 */
@Configuration
public class AjCaptchaStorageAutoConfiguration {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Bean(name = "AjCaptchaCacheService")
    @ConditionalOnMissingBean
    public CaptchaCacheService captchaCacheService(AjCaptchaProperties config) {
        //缓存类型redis/local/....
        CaptchaCacheService cache = CaptchaServiceFactory.getCache(config.getCacheType().name());
        if (cache instanceof RedisCaptchaServiceImpl){
            ((RedisCaptchaServiceImpl) cache).setStringRedisTemplate(stringRedisTemplate);
        }
        return cache;
    }
}
