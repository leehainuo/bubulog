package com.lihainuo.bubulog.repository.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihainuo.bubulog.domain.entity.ArticleCategoryRel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章所属分类关联表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
@Mapper
public interface ArticleCategoryRelMapper extends BaseMapper<ArticleCategoryRel> {

    /**
     * 根据文章 ID 删除关联记录
     * @param articleId
     * @return
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleCategoryRel>lambdaQuery()
                .eq(ArticleCategoryRel::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 获取关联记录
     * @param articleId
     * @return
     */
    default ArticleCategoryRel selectByArticleId(Long articleId) {
        return selectOne(Wrappers.<ArticleCategoryRel>lambdaQuery()
        .eq(ArticleCategoryRel::getArticleId, articleId));
    }
}
