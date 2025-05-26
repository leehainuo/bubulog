package com.lihainuo.bubulog.server.controller;


import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.aspect.ApiOperationLog;
import com.lihainuo.bubulog.domain.dto.AddCategoryDTO;
import com.lihainuo.bubulog.domain.dto.DeleteCategoryDTO;
import com.lihainuo.bubulog.domain.dto.QueryCategoryDTO;
import com.lihainuo.bubulog.domain.dto.UpdateCategoryDTO;
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
    private CategoryService categoryService;

    @PostMapping("/add")
    @ApiOperationLog(description = "添加分类")
    public Result addCategory(@RequestBody AddCategoryDTO addCategoryDTO) {
        return categoryService.addCategory(addCategoryDTO);
    }

    @PostMapping("/delete")
    @ApiOperationLog(description = "删除分类")
    public Result deleteCategory(@RequestBody DeleteCategoryDTO deleteCategoryDTO) {
        return categoryService.deleteCategory(deleteCategoryDTO);
    }

    @PostMapping("/update")
    @ApiOperationLog(description = "更新分类")
    public Result updateCategory(@RequestBody UpdateCategoryDTO updateCategoryDTO) {
        return categoryService.updateCategory(updateCategoryDTO);
    }

    @PostMapping("/query")
    @ApiOperationLog(description = "查询分类")
    public Result queryCategory(@RequestBody QueryCategoryDTO queryCategoryDTO) {
        return categoryService.queryCategory(queryCategoryDTO);
    }

    @PostMapping("/select/list")
    @ApiOperationLog(description = "下拉列表获取分类")
    public Result selectListCategory() {
        return categoryService.selectListCategory();
    }

}
