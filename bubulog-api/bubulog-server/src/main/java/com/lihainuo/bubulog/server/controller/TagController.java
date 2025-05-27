package com.lihainuo.bubulog.server.controller;

import com.lihainuo.bubulog.common.PageResult;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import com.lihainuo.bubulog.domain.dto.tag.AddTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.DeleteDTO;
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
    private TagService tagService;

    @PostMapping("/add")
    @ApiOperationLog(description = "添加标签")
    public Result addTag(@RequestBody AddTagDTO addTagDTO) {
        return tagService.addTag(addTagDTO);
    }

    @PostMapping("/delete")
    @ApiOperationLog(description = "删除标签")
    public Result deleteTag(@RequestBody DeleteDTO deleteDTO) {
        return tagService.deleteTag(deleteDTO);
    }

    @PostMapping("/update")
    @ApiOperationLog(description = "更新标签")
    public Result updateTag(@RequestBody UpdateTagDTO updateTagDTO) {
        return tagService.updateTag(updateTagDTO);
    }

    @PostMapping("/query")
    @ApiOperationLog(description = "查询标签")
    public PageResult queryTag(@RequestBody QueryTagDTO queryTagDTO) {
        return tagService.queryTag(queryTagDTO);
    }

}
