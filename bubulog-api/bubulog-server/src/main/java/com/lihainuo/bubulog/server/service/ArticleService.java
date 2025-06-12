package com.lihainuo.bubulog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.dto.article.*;
import com.lihainuo.bubulog.domain.entity.Article;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
public interface ArticleService extends IService<Article> {

    /**
     * 添加文章
     * @param dto
     * @return
     */
    Result addArticle(AddArticleDTO dto);

    /**
     * 删除文章
     * @param dto
     * @return
     */
    Result deleteArticle(DeleteArticleDTO dto);

    /**
     * 更新文章
     * @param dto
     * @return
     */
    Result updateArticle(UpdateArticleDTO dto);

    /**
     * 查询文章
     * @param dto
     * @return
     */
    Result queryArticle(QueryArticleDTO dto);

    /**
     * 获取文章详情
     * @param dto
     * @return
     */
    Result getArticleDetail(GetArticleDetailDTO dto);

    /**
     * 获取首页文章分页数据
     * @param dto
     * @return
     */
    Result queryArticleList(QueryArticleListDTO dto);
}
