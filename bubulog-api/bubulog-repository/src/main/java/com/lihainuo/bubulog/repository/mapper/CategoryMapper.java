package com.lihainuo.bubulog.repository.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lihainuo.bubulog.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章分类表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-22
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 根据分类名查询
     * @parm categoryName
     * @return
     */
    default Category selectByName(String categoryName) {
        // 构建查询条件
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName, categoryName);

        return selectOne(wrapper);
    }
}
