package com.lihainuo.bubulog.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lihainuo.bubulog.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    default Page<Article> selectByPage(Long current, Long size, String articleTitle, Date startDate, Date endDate) {
        // 分页对象 (查询第几页、每页多少数据)
        Page<Article> page = new Page<>(current, size);
        // 构建查询条件
        LambdaQueryWrapper<Article> wrapper = Wrappers.<Article>lambdaQuery()
                .like(StringUtils.isNotBlank(articleTitle), Article::getTitle, articleTitle) // like 模糊查询
                .ge(Objects.nonNull(startDate), Article::getCreateTime, startDate) // 大于等于 startDate
                .le(Objects.nonNull(endDate), Article::getCreateTime, endDate) // 小于等于 endDate
                .orderByDesc(Article::getCreateTime); // 按创建时间插叙

        return selectPage(page, wrapper);
    }
}
