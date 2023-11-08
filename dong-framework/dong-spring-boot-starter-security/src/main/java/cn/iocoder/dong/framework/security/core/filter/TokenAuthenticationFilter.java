package cn.iocoder.dong.framework.security.core.filter;

import cn.iocoder.dong.framework.security.config.SecurityProperties;
import cn.iocoder.dong.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.dong.framework.web.core.handler.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityProperties securityProperties;

    private final GlobalExceptionHandler globalExceptionHandler;

    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        // 继续过滤链
        filterChain.doFilter(request, response);
    }
}
