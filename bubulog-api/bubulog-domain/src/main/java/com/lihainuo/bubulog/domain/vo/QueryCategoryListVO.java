package com.lihainuo.bubulog.domain.vo;

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
public class QueryCategoryListVO {
    // 分类 ID
    private Long categoryId;

    // 分类名称
    private String categoryName;
}
