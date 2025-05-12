package com.lihainuo.bubulog.auth.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lihainuo.bubulog.auth.exception.UsernameOrPasswordNullException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 *     JWT认证过滤器
 * </p>
 *
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-08
 */

public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 指定用户登录的访问地址
     */
    public JwtAuthFilter() {
        super(new AntPathRequestMatcher("/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        // 解析提交的JSON数据
        JsonNode node = mapper.readTree(req.getReader());
        String username = node.get("username").asText();
        String password = node.get("password").asText();
        // 判断用户名、密码
        if (Objects.isNull(username) || Objects.isNull(password)) {
            throw new UsernameOrPasswordNullException("用户名或密码不能为空");
        }
        // 封装用户提供的登录凭证（用户名和密码）
        UsernamePasswordAuthenticationToken up2t =
                new UsernamePasswordAuthenticationToken(username, password);
        // 流程:封装的此凭证会去UserDetailServiceImpl中认证
        return getAuthenticationManager().authenticate(up2t);
    }
}
