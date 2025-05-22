package com.lihainuo.bubulog.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 默认的@SpringBootApplication复合注解的包扫描，只扫描自身模块
 * 多模块项目需要指定包扫描
 */

@SpringBootApplication
@ComponentScan({"com.lihainuo.bubulog.*"})
@MapperScan("com.lihainuo.bubulog.repository.mapper")
public class BubulogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BubulogApplication.class, args);
    }

}
