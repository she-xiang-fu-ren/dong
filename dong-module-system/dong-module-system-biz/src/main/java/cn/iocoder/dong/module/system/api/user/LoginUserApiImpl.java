package cn.iocoder.dong.module.system.api.user;

import cn.iocoder.dong.framework.common.util.json.JsonUtils;
import cn.iocoder.dong.module.system.api.user.dto.SysUserDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

import static cn.iocoder.dong.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.dong.module.system.ErrorCodeConstants.USER_NOT_EXISTS;

@Service
public class LoginUserApiImpl implements LoginUserApi{

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据token获取登录人信息
     *
     * @param token
     * @return
     */
    @Override
    public SysUserDTO getLoginUserAndToken(String token) {
        String user = stringRedisTemplate.opsForValue().get(token);
        if (user == null || Objects.equals(user, "")){
            throw exception(USER_NOT_EXISTS);
        }
        return JsonUtils.parseObject(user, SysUserDTO.class);
    }
}
