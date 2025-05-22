package com.lihainuo.bubulog.domain.dto;

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
public class AddCategoryDTO {
    private String categoryName;
}
