package com.lihainuo.bubulog.server.controller;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import com.lihainuo.bubulog.domain.dto.article.QueryArticleListDTO;
import com.lihainuo.bubulog.server.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihainuo
 * 未登录控制层
 * <p>
 *     无需用户登录即可访问
 * </p>
 */

@Slf4j
@RestController
@RequestMapping("")
public class NoAuthCotroller {

    @Autowired
    private ArticleService service;

    @PostMapping("/article/list")
    @ApiOperationLog(description = "获取首页文章分页数据")
    public Result queryArticleList(@RequestBody QueryArticleListDTO dto) {
        return service.queryArticleList(dto);
    }
}
