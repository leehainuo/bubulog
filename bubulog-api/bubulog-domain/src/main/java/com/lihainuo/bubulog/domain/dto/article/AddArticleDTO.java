package com.lihainuo.bubulog.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-06-07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddArticleDTO {
    private String title;

    private String content;

    private String cover;

    private String summary;

    private Long categoryId;

    private List<String> tags;

}
