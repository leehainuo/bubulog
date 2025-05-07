package com.lihainuo.bubulog.web.controller;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {

    @ApiOperationLog(description = "测试API请求日志切面")
    @PostMapping("/test")
    public Result ApiTest() {
        // 自定义一个业务异常
        // throw new BusinessException(ResultEnum.PRODUCT_ERROR);
        // 自定义一个运行时异常
        // int e = 1 / 0;

        return Result.success("Hello World");
    }

}
