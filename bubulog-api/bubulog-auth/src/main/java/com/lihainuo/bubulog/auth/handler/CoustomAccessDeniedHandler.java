package com.lihainuo.bubulog.auth.handler;

import com.lihainuo.bubulog.auth.utils.ResultUtil;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.enums.ResultEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 *     权限处理器
 * </p>
 *
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-09
 */

@Slf4j
@Component
public class CoustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("登录成功访问收保护的资源，但是权限不够: ", accessDeniedException);
        // 预留，后面引入多角色时会用到
        ResultUtil.fail(res, Result.fail(ResultEnum.LOW_AUTHORITY));
    }
}
