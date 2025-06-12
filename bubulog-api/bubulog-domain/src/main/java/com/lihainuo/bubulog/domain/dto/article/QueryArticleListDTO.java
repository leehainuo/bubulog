package com.lihainuo.bubulog.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-06-12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryArticleListDTO {
    // 当前页码, 默认第一页
    private Long current = 1L;

    // 每页展示数量 默认 10 条数据
    private Long size = 10L;
}
