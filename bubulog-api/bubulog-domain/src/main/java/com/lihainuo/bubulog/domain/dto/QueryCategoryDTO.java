package com.lihainuo.bubulog.domain.dto;

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
public class QueryCategoryDTO {

    // 当前页码, 默认第一页
    private Long current = 1L;

    // 每页展示数量 默认 10 条数据
    private Long size = 10L;

    // 分类名称
    private String categoryName;

    // 创建的起始日期
    private Date startDate;

    // 创建的结束日期
    private Date endDate;

}
