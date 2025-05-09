package com.lihainuo.bubulog.auth.handler;

import com.lihainuo.bubulog.auth.exception.UsernameOrPasswordNullException;
import com.lihainuo.bubulog.auth.utils.ResultUtil;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.enums.ResultEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 *     认证失败处理器
 * </p>
 *
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-08
 */

@Slf4j
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        log.warn("AuthenticationException: ", e);
        if (e instanceof UsernameOrPasswordNullException) {
            // 用户名或密码为空
            ResultUtil.fail(res, Result.fail(e.getMessage()));
            return;
        } else if (e instanceof BadCredentialsException) {
            // 用户名或密码错误
            ResultUtil.fail(res, Result.fail(ResultEnum.PRODUCT_ERROR));
            return;
        }

        // 登录失败
        ResultUtil.fail(res, Result.fail(ResultEnum.PRODUCT_ERROR));
    }
}
