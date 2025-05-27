package com.lihainuo.bubulog.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lihainuo.bubulog.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章标签表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-27
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据分类名称查询
     * @param tagName
     * @return
     */
    default Tag selectByName(String tagName) {
        // 构建查询条件
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getName, tagName);

        return selectOne(wrapper);
    };
}
