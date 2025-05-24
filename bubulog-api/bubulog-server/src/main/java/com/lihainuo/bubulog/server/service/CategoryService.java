package com.lihainuo.bubulog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.dto.AddCategoryDTO;
import com.lihainuo.bubulog.domain.dto.DeleteCategoryDTO;
import com.lihainuo.bubulog.domain.dto.QueryCategoryDTO;
import com.lihainuo.bubulog.domain.entity.Category;

/**
 * <p>
 * 文章分类表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-22
 */
public interface CategoryService extends IService<Category> {
    /**
     *  添加分类
     * @param addCategoryDTO
     * @return
     */
    Result addCategory(AddCategoryDTO addCategoryDTO);

    /**
     * 查询分类
     * @param queryCategoryDTO
     * @return
     */
    Result queryCategory(QueryCategoryDTO queryCategoryDTO);

    /**
     * 删除分类
     * @param deleteCategoryDTO
     * @return
     */
    Result deleteCategory(DeleteCategoryDTO deleteCategoryDTO);

    /**
     * 下拉列表获取分类
     * @param
     * @return
     */
    Result selectListCategory();
}
