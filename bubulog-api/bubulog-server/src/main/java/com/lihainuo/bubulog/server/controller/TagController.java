package com.lihainuo.bubulog.server.controller;

import com.lihainuo.bubulog.common.PageResult;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import com.lihainuo.bubulog.domain.dto.tag.AddTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.DeleteTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.QueryTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.UpdateTagDTO;
import com.lihainuo.bubulog.server.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章标签表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-27
 */
@RestController
@RequestMapping("/admin/tag")
public class TagController {

    @Autowired
    private TagService service;

    @PostMapping("/add")
    @ApiOperationLog(description = "添加标签")
    public Result addTag(@RequestBody AddTagDTO dto) {
        return service.addTag(dto);
    }

    @PostMapping("/delete")
    @ApiOperationLog(description = "删除标签")
    public Result deleteTag(@RequestBody DeleteTagDTO dto) {
        return service.deleteTag(dto);
    }

    @PostMapping("/update")
    @ApiOperationLog(description = "更新标签")
    public Result updateTag(@RequestBody UpdateTagDTO dto) {
        return service.updateTag(dto);
    }

    @PostMapping("/query")
    @ApiOperationLog(description = "查询标签")
    public PageResult queryTag(@RequestBody QueryTagDTO dto) {
        return service.queryTag(dto);
    }

    @PostMapping("/select/list")
    @ApiOperationLog(description = "下拉列表获取标签")
    public Result selectListTag() {
        return service.selectListTag();
    }
}
