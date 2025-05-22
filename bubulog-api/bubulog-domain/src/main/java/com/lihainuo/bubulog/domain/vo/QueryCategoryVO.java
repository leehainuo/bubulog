package com.lihainuo.bubulog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryCategoryVO {

    // 分类 ID
    private Long categoryId;

    // 分类名称
    private String categoryName;

    // 创建时间
    private Date createTime;
}
