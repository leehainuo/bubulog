package com.lihainuo.bubulog.server.controller;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import com.lihainuo.bubulog.domain.dto.UpdateBlogSettingsDTO;
import com.lihainuo.bubulog.server.service.BlogSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客设置表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-03
 */
@RestController
@RequestMapping("/admin/blog/settings")
public class BlogSettingsController {

    @Autowired
    private BlogSettingsService service;

    @PostMapping("/update")
    @ApiOperationLog(description = "更新博客设置")
    public Result updateBlogSettings(@RequestBody UpdateBlogSettingsDTO dto) {
        return service.updateBlogSettings(dto);
    }

    @PostMapping("/query")
    @ApiOperationLog(description = "获取博客设置详情")
    public Result queryBlogSettings() {
        return service.queryBlogSettings();
    }
}
