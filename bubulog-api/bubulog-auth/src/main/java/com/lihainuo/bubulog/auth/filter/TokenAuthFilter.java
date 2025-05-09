package com.lihainuo.bubulog.auth.filter;

import com.lihainuo.bubulog.auth.handler.AuthEntryPointHandler;
import com.lihainuo.bubulog.auth.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <p>
 *     Token过滤器
 * </p>
 *
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-09
 */

@Slf4j
public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthEntryPointHandler authEntryPointHandler;



    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取 Authorization
        String header = req.getHeader("Authorization");

        // 判断值是否为 Bearer 开头
        if (header != null && header.startsWith("Bearer ")) {
            // 截取 Token（确保不越界）
            String token =header.substring(7);
            log.info("Token: {}", token);

            // 检查 Token 是否非空（去空格后非空）
            if (token != null && !token.trim().isEmpty()) {
                try {
                    // 校验 Token 有效性
                    jwtUtil.validateToken(token);
                } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
                    authEntryPointHandler.commence(req, res,
                            new AuthenticationServiceException("Token 不可用"));
                    return;
                } catch (ExpiredJwtException e) {
                    authEntryPointHandler.commence(req, res, new AuthenticationServiceException("Token 已失效"));
                    return;
                }

                // 从 Token 解析用户名
                Claims claims = jwtUtil.parseToken(token);
                String username = claims.get("username", String.class);

                // 检查用户名非空且当前无认证信息
                if (username != null && !username.trim().isEmpty()
                        && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 加载用户详情
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // 构建认证信息并存入上下文
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        // 继续执行过滤器链
        filterChain.doFilter(req, res);
    }
}
