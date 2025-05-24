package com.lihainuo.bubulog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectCategoryVO {
    // 下拉列表的展示文字
    private String label;
    // 下拉列表的 value 值
    private Object value;
}
