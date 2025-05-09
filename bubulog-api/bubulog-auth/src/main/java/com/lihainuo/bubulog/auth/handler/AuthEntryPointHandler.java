package com.lihainuo.bubulog.auth.handler;

import com.lihainuo.bubulog.auth.utils.ResultUtil;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.enums.ResultEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 *      处理当用户未登录时，访问受保护的资源的情况
 * </p>
 *
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-09
 */


@Slf4j
@Component
public class AuthEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        log.warn("用户未登录访问受保护的资源: ", authException);
        if (authException instanceof InsufficientAuthenticationException) {
            ResultUtil.fail(res, HttpStatus.UNAUTHORIZED.value(), Result.fail(ResultEnum.UNAUTHORIZED));
            return;
        }

        ResultUtil.fail(res, HttpStatus.UNAUTHORIZED.value(), Result.fail(authException.getMessage()));
    }
}
