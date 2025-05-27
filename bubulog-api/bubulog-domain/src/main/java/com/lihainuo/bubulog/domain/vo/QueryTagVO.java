package com.lihainuo.bubulog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryTagVO {
    // 标签 ID
    private Long tagId;

    // 标签名称
    private String tagName;

    // 创建时间
    private Date createDate;
}
