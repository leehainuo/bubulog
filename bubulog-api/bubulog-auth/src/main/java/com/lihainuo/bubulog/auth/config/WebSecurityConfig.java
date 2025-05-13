package com.lihainuo.bubulog.auth.config;

import com.lihainuo.bubulog.auth.filter.JwtAuthFilter;
import com.lihainuo.bubulog.auth.filter.TokenAuthFilter;
import com.lihainuo.bubulog.auth.handler.AuthEntryPointHandler;
import com.lihainuo.bubulog.auth.handler.AuthFailureHandler;
import com.lihainuo.bubulog.auth.handler.AuthSuccessHandler;
import com.lihainuo.bubulog.auth.handler.CoustomAccessDeniedHandler;
import com.lihainuo.bubulog.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 *     Spring Security 配置类
 * </p>
 *
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-08
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;
    private final AuthEntryPointHandler authEntryPointHandler;
    private final CoustomAccessDeniedHandler accessDeniedHandler;


    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder, AuthSuccessHandler authSuccessHandler, AuthFailureHandler authFailureHandler, AuthEntryPointHandler authEntryPointHandler, CoustomAccessDeniedHandler accessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authSuccessHandler = authSuccessHandler;
        this.authFailureHandler = authFailureHandler;
        this.authEntryPointHandler = authEntryPointHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 自定义JWT过滤器
    @Bean
    public JwtAuthFilter jwtAuthFilter(AuthenticationManager authenticationManager) throws Exception {

        JwtAuthFilter filter = new JwtAuthFilter();
        filter.setAuthenticationManager(authenticationManager);
        // 设置登录认证对应的处理类（成功处理、失败处理）
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationFailureHandler(authFailureHandler);
        return filter;
    }
    // 自定义Token过滤器
    @Bean
    public TokenAuthFilter tokenAuthFilter() {
        return new TokenAuthFilter();
    }

    // 配置Dao认证提供者（Spring Security 6+ 推荐独立Bean）
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthFilter jwtAuthFilter,
            TokenAuthFilter tokenAuthFilter
    ) throws Exception {
        http
            .csrf().disable() // 禁用csrf
            .formLogin().disable() // 禁用表单登录
            .authenticationProvider(
                daoAuthenticationProvider()
            ) // 配置认证提供者
            .addFilterBefore(
                jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
            ) // 添加自定义JWT过滤器
            .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").authenticated() // 认证所有以 /admin 为前缀的 URL
                .anyRequest().permitAll() // 其他 URL 无需认证 直接放行
            )
            .sessionManagement().sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
            ) // 前后端分离，无需创建会话
            .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPointHandler) // 处理用户未登录成功后访问受保护的资源
            .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) // 处理登录成功的权限不足访问受保护资源
        ;

        return http.build();
    }

}
