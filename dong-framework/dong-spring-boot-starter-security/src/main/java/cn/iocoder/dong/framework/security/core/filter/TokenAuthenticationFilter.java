package cn.iocoder.dong.framework.security.core.filter;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.framework.common.util.servlet.ServletUtils;
import cn.iocoder.dong.framework.security.config.SecurityProperties;
import cn.iocoder.dong.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.dong.framework.web.core.handler.GlobalExceptionHandler;
import cn.iocoder.dong.module.system.api.user.LoginUserApi;
import cn.iocoder.dong.module.system.api.user.dto.SysUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
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

    private final LoginUserApi loginUserApi;

    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotEmpty(token)){
            try {
                SysUserDTO loginUserAndToken = loginUserApi.getLoginUserAndToken(token);
                //设置user到全局里面
                SecurityFrameworkUtils.setLoginUser(loginUserAndToken,request);
            }catch (Throwable ex){
                //将错误抛给全局异常
                CommonResult<?> result = globalExceptionHandler.allExceptionHandler(request, ex);
                ServletUtils.writeJSON(response, result);
                return;
            }
        }

        // 继续过滤链
        filterChain.doFilter(request, response);
    }
}
