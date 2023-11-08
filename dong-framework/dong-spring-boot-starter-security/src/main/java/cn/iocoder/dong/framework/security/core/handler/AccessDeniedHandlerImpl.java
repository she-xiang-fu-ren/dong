package cn.iocoder.dong.framework.security.core.handler;

import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.framework.common.util.servlet.ServletUtils;
import cn.iocoder.dong.framework.security.core.util.SecurityFrameworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.iocoder.dong.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 打印 warn 的原因是，不定期合并 warn，看看有没恶意破坏
        log.warn("[commence][访问 URL({}) 时，用户({}) 权限不够]", request.getRequestURI(),
                SecurityFrameworkUtils.getLoginUserId(), accessDeniedException);
        ServletUtils.writeJSON(response, CommonResult.error(FORBIDDEN));
    }
}
