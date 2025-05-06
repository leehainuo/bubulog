package com.lihainuo.bubulog.web.demos.web;

import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {

    @ApiOperationLog(description = "测试API请求日志切面")
    @PostMapping("/test")
    public User ApiTest(@RequestBody User user) {
        return user;
    }

}
