package cn.iocoder.dong.module.system.service.help;

import cn.hutool.core.lang.UUID;
import cn.iocoder.dong.framework.common.util.map.MapUtils;
import cn.iocoder.dong.framework.common.util.json.JsonUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * 使用jwt来存储用户token，则不需要后端来存储session了
 *
 * @author YiHui
 * @date 2022/12/5
 */
@Slf4j
@Component
public class UserSessionHelper {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Component
    @Data
    @ConfigurationProperties("dong.jwt")
    public static class JwtProperties {
        /**
         * 签发人
         */
        private String issuer;
        /**
         * 密钥
         */
        private String secret;
        /**
         * 有效期，毫秒时间戳
         */
        private Long expire;
    }

    private final JwtProperties jwtProperties;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    public UserSessionHelper(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
        verifier = JWT.require(algorithm).withIssuer(jwtProperties.getIssuer()).build();
    }

    public String genSession(Long userId) {
        // 1.生成jwt格式的会话，内部持有有效期，用户信息
        String session = JsonUtils.toStr(MapUtils.create("s", UUID.randomUUID().toString().replace("-",""), "u", userId));
        String token = JWT.create().withIssuer(jwtProperties.getIssuer()).withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpire()))
                .withPayload(session)
                .sign(algorithm);

        // 2.使用jwt生成的token时，后端可以不存储这个session信息, 完全依赖jwt的信息
        // 但是需要考虑到用户登出，需要主动失效这个token，而jwt本身无状态，所以再这里的redis做一个简单的token -> userId的缓存，用于双重判定
        stringRedisTemplate.opsForValue().set(token, String.valueOf(userId), jwtProperties.getExpire() / 1000);
//        RedisClient.setStrWithExpire(token, String.valueOf(userId), jwtProperties.getExpire() / 1000);
        return token;
    }

    public void removeSession(String session) {
        stringRedisTemplate.delete(session);
    }

    /**
     * 根据会话获取用户信息
     *
     * @param session
     * @return
     */
    public Long getUserIdBySession(String session) {
        // jwt的校验方式，如果token非法或者过期，则直接验签失败
        try {
            DecodedJWT decodedJWT = verifier.verify(session);
            String pay = new String(Base64Utils.decodeFromString(decodedJWT.getPayload()));
            // jwt验证通过，获取对应的userId
            String userId = String.valueOf(JsonUtils.toObj(pay, HashMap.class).get("u"));

            // 从redis中获取userId，解决用户登出，后台失效jwt token的问题
            String user = stringRedisTemplate.opsForValue().get(session);
            if (user == null || !Objects.equals(userId, user)) {
                return null;
            }
            return Long.valueOf(user);
        } catch (Exception e) {
            log.info("jwt token校验失败! token: {}, msg: {}", session, e.getMessage());
            return null;
        }
    }
}
