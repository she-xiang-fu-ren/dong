package cn.iocoder.dong.framework.security.core.handler;

import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.framework.common.util.servlet.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.iocoder.dong.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.debug("[commence][访问 URL({}) 时，没有登录]", request.getRequestURI(), authException);
        ServletUtils.writeJSON(response, CommonResult.error(UNAUTHORIZED));
    }
}
