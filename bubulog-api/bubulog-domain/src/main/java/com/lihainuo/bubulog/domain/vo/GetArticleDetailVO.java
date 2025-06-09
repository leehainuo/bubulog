package com.lihainuo.bubulog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-06-08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetArticleDetailVO {
    // 文章 ID
    private Long articleId;
    // 文章标题
    private String articleTitle;
    // 文章封面
    private String articleCover;
    // 文章摘要
    private String articleSummary;
    // 文章内容
    private String articleContent;
    // 分类 ID
    private Long categoryId;
    // 标签 集合
    private List<String> tags;

}
