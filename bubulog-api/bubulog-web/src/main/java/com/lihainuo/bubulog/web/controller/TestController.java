package com.lihainuo.bubulog.web.controller;

import com.lihainuo.bubulog.auth.config.JwtConfig;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("")
public class TestController {

    @Resource
    private JwtConfig jwtConfig;

    @ApiOperationLog(description = "测试API请求日志切面")
    @GetMapping("/admin/test")
    public Result ApiTest() {
        // 自定义一个业务异常
        // throw new BusinessException(ResultEnum.PRODUCT_ERROR);
        // 自定义一个运行时异常
        // int e = 1 /
        // 测试Jwt
        String key = jwtConfig.getKey();
        log.info("Key: {}", key);
        // 测试Spring Security 后续将此模块的Security依赖删除
        return Result.success("Hello World");
    }

}
