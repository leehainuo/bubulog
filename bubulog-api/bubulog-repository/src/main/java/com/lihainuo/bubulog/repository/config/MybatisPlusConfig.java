package com.lihainuo.bubulog.repository.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @date 2025/5/7 08:34
 */

@Configuration
@MapperScan("com.lihainuo.bubulog.repository.mapper")
public class MybatisPlusConfig {
}
