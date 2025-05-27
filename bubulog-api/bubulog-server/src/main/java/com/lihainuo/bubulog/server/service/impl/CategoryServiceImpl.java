package com.lihainuo.bubulog.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihainuo.bubulog.common.PageResult;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.enums.ResultEnum;
import com.lihainuo.bubulog.common.exception.BusinessException;
import com.lihainuo.bubulog.domain.dto.category.AddCategoryDTO;
import com.lihainuo.bubulog.domain.dto.category.DeleteCategoryDTO;
import com.lihainuo.bubulog.domain.dto.category.QueryCategoryDTO;
import com.lihainuo.bubulog.domain.dto.category.UpdateCategoryDTO;
import com.lihainuo.bubulog.domain.entity.Category;
import com.lihainuo.bubulog.domain.vo.QueryCategoryVO;
import com.lihainuo.bubulog.domain.vo.SelectCategoryVO;
import com.lihainuo.bubulog.repository.mapper.CategoryMapper;
import com.lihainuo.bubulog.server.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章分类表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-22
 */

@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 添加分类
     * @param addCategoryDTO
     * @return
     */
    @Override
    public Result addCategory(AddCategoryDTO addCategoryDTO) {
        String categoryName = addCategoryDTO.getCategoryName();
        // 先判断该分类是否已经存在
        Category category = this.baseMapper.selectByName(categoryName);

        if (Objects.nonNull(category)) {
            log.warn("分类名称： {}，已存在", categoryName);
            throw new BusinessException(ResultEnum.CATEGORY_NAME_EXIST);
        }

        // 不存在则添加
        Category newCategory = Category.builder()
                .name(categoryName.trim())
                .build();

        this.baseMapper.insert(newCategory);

        return Result.success();
    }

    /**
     * 删除分类
     * @param deleteCategoryDTO
     * @return
     */
    @Override
    public Result deleteCategory(DeleteCategoryDTO deleteCategoryDTO) {
        Long categoryId = deleteCategoryDTO.getCategoryId();
        this.baseMapper.deleteById(categoryId);
        return Result.success();
    }

    /**
     * 更新分类
     * @param updateCategoryDTO
     * @return
     */
    @Override
    public Result updateCategory(UpdateCategoryDTO updateCategoryDTO) {
        // 获取更新分类 Id、名称
        Long categoryId = updateCategoryDTO.getCategoryId();
        String categoryName = updateCategoryDTO.getCategoryName();

        // 构建更新对象
        Category category = Category.builder()
                .id(categoryId)
                .name(categoryName)
                .updateTime(new Date())
                .build();
        this.baseMapper.updateById(category);
        return Result.success("分类名称更新成功");
    }

    /**
     * 查询分类
     * @param queryCategoryDTO
     * @return
     */
    @Override
    public PageResult queryCategory(QueryCategoryDTO queryCategoryDTO) {
        // 获取当前也、以及每页需要展示的数量
        Long current = queryCategoryDTO.getCurrent();
        Long size = queryCategoryDTO.getSize();
        // 分页对象
        Page<Category> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        String categoryName = queryCategoryDTO.getCategoryName();
        Date startDate = queryCategoryDTO.getStartDate();
        Date endDate = queryCategoryDTO.getEndDate();

        wrapper
            .like(StringUtils.isNotBlank(categoryName), Category::getName, categoryName.trim()) // like 模块查询
            .ge(Objects.nonNull(startDate), Category::getCreateTime, startDate) // 大于等于 startDate
            .le(Objects.nonNull(endDate), Category::getCreateTime, endDate) // 小于等于 endDate
            .orderByDesc(Category::getCreateTime); // 按创建时间倒叙

        // 执行分页查询
        Page<Category> categoryPage = this.baseMapper
                .selectPage(page, wrapper);

        List<Category> categories = categoryPage.getRecords();
        List<QueryCategoryVO> vos = null;
        if (!CollectionUtils.isEmpty(categories)) {
            vos = categories.stream()
                    .map(item -> QueryCategoryVO.builder()  // 使用 VO 的 Builder
                            .categoryId(item.getId())
                            .categoryName(item.getName())
                            .createDate(item.getCreateTime())
                            .build()  // 必须调用 build() 生成 VO 实例
                    )
                    .collect(Collectors.toList());
        }

        return PageResult.success(categoryPage, vos);

    }


    /**
     * 下拉列表获取分类
     * @param
     * @return
     */
    @Override
    public Result selectListCategory() {
        // 查询所有分类
        List<Category> categories = this.baseMapper.selectList(null);
        // 转换为VO
        List<SelectCategoryVO> vos = null;
        if (!CollectionUtils.isEmpty(categories)) {
            // 将分类 ID -> Value 分类名称 -> Label
            vos = categories.stream()
                    .map(item -> SelectCategoryVO.builder()
                            .label(item.getName())
                            .value(item.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Result.success(vos);
    }

}
