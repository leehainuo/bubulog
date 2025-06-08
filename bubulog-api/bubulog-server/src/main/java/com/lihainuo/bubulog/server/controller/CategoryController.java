package com.lihainuo.bubulog.server.controller;


import com.lihainuo.bubulog.common.PageResult;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import com.lihainuo.bubulog.domain.dto.category.AddCategoryDTO;
import com.lihainuo.bubulog.domain.dto.category.DeleteCategoryDTO;
import com.lihainuo.bubulog.domain.dto.category.QueryCategoryDTO;
import com.lihainuo.bubulog.domain.dto.category.UpdateCategoryDTO;
import com.lihainuo.bubulog.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章分类表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-22
 */

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/add")
    @ApiOperationLog(description = "添加分类")
    public Result addCategory(@RequestBody AddCategoryDTO dto) {
        return service.addCategory(dto);
    }

    @PostMapping("/delete")
    @ApiOperationLog(description = "删除分类")
    public Result deleteCategory(@RequestBody DeleteCategoryDTO dto) {
        return service.deleteCategory(dto);
    }

    @PostMapping("/update")
    @ApiOperationLog(description = "更新分类")
    public Result updateCategory(@RequestBody UpdateCategoryDTO dto) {
        return service.updateCategory(dto);
    }

    @PostMapping("/query")
    @ApiOperationLog(description = "查询分类")
    public PageResult queryCategory(@RequestBody QueryCategoryDTO dto) {
        return service.queryCategory(dto);
    }

    @PostMapping("/select/list")
    @ApiOperationLog(description = "下拉列表获取分类")
    public Result selectListCategory() {
        return service.selectListCategory();
    }

}
