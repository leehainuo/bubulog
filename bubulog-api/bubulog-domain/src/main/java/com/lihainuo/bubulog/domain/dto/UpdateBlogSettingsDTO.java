package com.lihainuo.bubulog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-06-03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBlogSettingsDTO {

    // 博客 Logo
    private String logo;

    // 博客 博客名
    private String name;

    // 博客 作者
    private String author;

    // 博客 介绍语
    private String introduction;

    // 博客 头像
    private String avatar;

    /**
     * 博客其他
     */
    private String githubHomePage;
    private String juejinHomePage;
    private String wechatAccount;
    private String qqAccount;


}
