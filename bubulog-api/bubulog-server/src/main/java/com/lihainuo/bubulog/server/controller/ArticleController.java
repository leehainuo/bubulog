package com.lihainuo.bubulog.server.controller;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import com.lihainuo.bubulog.domain.dto.article.*;
import com.lihainuo.bubulog.server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @PostMapping("/add")
    @ApiOperationLog(description = "添加文章")
    public Result addArticle(@RequestBody AddArticleDTO dto) {
        return service.addArticle(dto);
    }

    @PostMapping("/delete")
    @ApiOperationLog(description = "删除文章")
    public Result deleteArticle(@RequestBody DeleteArticleDTO dto) {
        return service.deleteArticle(dto);
    }

    @PostMapping("/update")
    @ApiOperationLog(description = "更新文章")
    public Result updateArticle(@RequestBody UpdateArticleDTO dto) {
        return service.updateArticle(dto);
    }

    @PostMapping("/query")
    @ApiOperationLog(description = "查询文章")
    public Result queryArticle(@RequestBody QueryArticleDTO dto) {
        return service.queryArticle(dto);
    }

    @PostMapping("/detail")
    @ApiOperationLog(description = "获取文章详情")
    public Result getArticleDetail(@RequestBody GetArticleDetailDTO dto) {
        return service.getArticleDetail(dto);
    }

    @PostMapping("/list")
    @ApiOperationLog(description = "获取首页文章分页数据")
    public Result queryArticleList(@RequestBody QueryArticleListDTO dto) {
        return service.queryArticleList(dto);
    }
}
