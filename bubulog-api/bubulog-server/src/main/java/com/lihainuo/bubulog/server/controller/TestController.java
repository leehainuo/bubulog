package com.lihainuo.bubulog.server.controller;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("")
public class TestController {


    @ApiOperationLog(description = "测试API请求日志切面")
    @GetMapping("/test")
    public Result ApiTest() {
        // 自定义一个业务异常
        // throw new BusinessException(ResultEnum.PRODUCT_ERROR);
        // 自定义一个运行时异常
        // int e = 1 /
        // 测试Spring Security 后续将此模块的Security依赖删除
        return Result.success("Hello World");
    }

    @ApiOperationLog(description = "测试鉴权接口")
    @PostMapping("/admin/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result AuthTest() {
        log.info("测试成功...");
        return Result.success("测试成功...");
    }

}
