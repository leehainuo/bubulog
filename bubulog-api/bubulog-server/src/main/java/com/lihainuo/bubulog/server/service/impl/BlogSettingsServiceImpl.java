package com.lihainuo.bubulog.server.service.impl;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.dto.UpdateBlogSettingsDTO;
import com.lihainuo.bubulog.domain.entity.BlogSettings;

import com.lihainuo.bubulog.repository.mapper.BlogSettingsMapper;
import com.lihainuo.bubulog.server.service.BlogSettingsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客设置表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-03
 */
@Service
public class BlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettings> implements BlogSettingsService {

    @Override
    public Result updateBlogSettings(UpdateBlogSettingsDTO updateBlogSettingsDTO) {
        // dto -> entity
        BlogSettings blogSettings = BlogSettings.builder()
                .id(1L)
                .logo(updateBlogSettingsDTO.getLogo())
                .name(updateBlogSettingsDTO.getName())
                .author(updateBlogSettingsDTO.getAuthor())
                .introduction(updateBlogSettingsDTO.getIntroduction())
                .avatar(updateBlogSettingsDTO.getAvatar())
                .githubHomepage(updateBlogSettingsDTO.getGithubHomePage())
                .juejinHomepage(updateBlogSettingsDTO.getJuejinHomePage())
                .wechatAccount(updateBlogSettingsDTO.getWechatAccount())
                .qqAccount(updateBlogSettingsDTO.getQqAccount())
                .build();
        // 保存或更新
        saveOrUpdate(blogSettings);

        return Result.success();
    }
}
