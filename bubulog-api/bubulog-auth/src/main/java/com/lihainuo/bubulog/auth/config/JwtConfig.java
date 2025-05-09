package com.lihainuo.bubulog.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     JWT 配置类
 * </p>
 *
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-08
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt") // 绑定前缀为 jwt 的配置
public class JwtConfig {
    private String key;
    private long ttl;
}
