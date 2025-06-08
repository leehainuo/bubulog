package com.lihainuo.bubulog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-06-08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryArticleVO {
    // 文章 ID
    private Long articleId;

    // 文章标题
    private String articleTitle;

    // 文章封面
    private String articleCover;

    // 创建时间
    private Date createDate;
}