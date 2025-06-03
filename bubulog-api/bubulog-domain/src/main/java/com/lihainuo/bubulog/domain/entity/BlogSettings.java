package com.lihainuo.bubulog.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 博客设置表
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-03
 */
@Getter
@Setter
@Builder
@TableName("t_blog_settings")
public class BlogSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 博客Logo
     */
    private String logo;

    /**
     * 博客名称
     */
    private String name;

    /**
     * 作者名
     */
    private String author;

    /**
     * 介绍语
     */
    private String introduction;

    /**
     * 作者头像
     */
    private String avatar;

    /**
     * GitHub 主页访问地址
     */
    private String githubHomepage;

    /**
     * 稀土掘金 主页访问地址
     */
    private String juejinHomepage;

    /**
     * 微信号
     */
    private String wechatAccount;

    /**
     * QQ号
     */
    private String qqAccount;
}
