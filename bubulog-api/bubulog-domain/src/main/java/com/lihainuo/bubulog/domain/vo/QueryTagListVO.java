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
public class QueryTagListVO {
    // 标签 ID
    private Long tagId;

    // 标签名称
    private String tagName;
}
