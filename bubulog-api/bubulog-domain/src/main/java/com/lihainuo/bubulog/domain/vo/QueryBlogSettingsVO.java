package com.lihainuo.bubulog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-06-04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryBlogSettingsVO {

    // 博客 Logo
    private String logo;
    // 博客 博客名
    private String name;
    // 博客 作者
    private String author;
    // 博客 介绍
    private String introduction;
    // 博客 作者头像
    private String avatar;
    /**
     * 其余
     */
    private String githubHomePage;
    private String juejinHomePage;
    private String wechatAccount;
    private String qqAccount;

}
