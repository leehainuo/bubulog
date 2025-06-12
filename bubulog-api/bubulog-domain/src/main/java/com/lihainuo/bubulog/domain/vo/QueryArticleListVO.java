package com.lihainuo.bubulog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-06-12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryArticleListVO {
    private Long articleId;
    private String articleCover;
    private String articleTitle;
    private Date createDate;
    private String articleSummary;
    /**
     * 文章分类
     */
    private QueryCategoryListVO category;

    /**
     * 文章标签
     */
    private List<QueryTagListVO> tags;
}
