package com.lihainuo.bubulog.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihainuo.bubulog.domain.entity.ArticleContent;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章内容表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
@Mapper
public interface ArticleContentMapper extends BaseMapper<ArticleContent> {

    /**
     * 根据文章 ID 删除记录
     * @param articleId
     * @return
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleContent>lambdaQuery()
                .eq(ArticleContent::getArticleId, articleId));
    }

    default int updateByArticleId(Long articleId, ArticleContent articleContent) {
        return update(articleContent, Wrappers.<ArticleContent>lambdaQuery()
        .eq(ArticleContent::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 获取文章内容
     * @param articleId
     * @return
     */
    default ArticleContent selectByArticleId(Long articleId) {
        return selectOne(Wrappers.<ArticleContent>lambdaQuery()
        .eq(ArticleContent::getArticleId, articleId));
    }

}
