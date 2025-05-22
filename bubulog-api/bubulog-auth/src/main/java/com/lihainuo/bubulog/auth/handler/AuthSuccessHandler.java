package com.lihainuo.bubulog.auth.handler;

import com.lihainuo.bubulog.auth.utils.JwtUtil;
import com.lihainuo.bubulog.auth.utils.ResultUtil;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.vo.LoginVO;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * <p>
 *     认证成功处理器
 * </p>
 *
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025/5/8 16:40
 */

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 从 Authentication 对象中获取用户的 UserDetails 实例
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // 通过用户名生成Token
        String username = userDetails.getUsername();
        String token = "Bearer " + jwtUtil.generateToken(username);

        // 返回Token
        LoginVO loginVO = LoginVO.builder().token(token).build();

        ResultUtil.ok(response, Result.success(loginVO));

    }
}
