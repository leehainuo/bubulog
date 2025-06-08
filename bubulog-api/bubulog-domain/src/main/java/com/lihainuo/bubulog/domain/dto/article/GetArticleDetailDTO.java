package com.lihainuo.bubulog.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-06-08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetArticleDetailDTO {
    private Long articleId;
}
