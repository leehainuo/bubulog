package com.lihainuo.bubulog.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihainuo.bubulog.domain.entity.ArticleTagRel;
import com.lihainuo.bubulog.repository.config.InsertBatchMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文章对应标签关联表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
@Mapper
public interface ArticleTagRelMapper extends InsertBatchMapper<ArticleTagRel> {

    /**
     * 根据文章 ID 删除关联记录
     * @param articleId
     * @return
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleTagRel>lambdaQuery()
                .eq(ArticleTagRel::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 获取关联记录集合
     * @param articleId
     * @return
     */
    default List<ArticleTagRel> selectByArticleId(Long articleId) {
        return selectList(Wrappers.<ArticleTagRel>lambdaQuery()
        .eq(ArticleTagRel::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 集合批量查询
     * @param articleIds
     * @return
     */
    default List<ArticleTagRel> selectByArticleIds(List<Long> articleIds) {
        return selectList(Wrappers.<ArticleTagRel>lambdaQuery()
                .in(ArticleTagRel::getArticleId, articleIds));
    }
}
