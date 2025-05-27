package com.lihainuo.bubulog.domain.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTagDTO {
    private Long tagId;
    private String tagName;
}
