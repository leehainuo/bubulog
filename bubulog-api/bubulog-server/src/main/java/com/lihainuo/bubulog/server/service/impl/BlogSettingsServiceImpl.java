package com.lihainuo.bubulog.server.service.impl;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.dto.UpdateBlogSettingsDTO;
import com.lihainuo.bubulog.domain.entity.BlogSettings;

import com.lihainuo.bubulog.domain.vo.QueryBlogSettingsVO;
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

    /**
     * 更新博客设置
     * @param dto
     * @return
     */
    @Override
    public Result updateBlogSettings(UpdateBlogSettingsDTO dto) {
        // dto -> entity
        BlogSettings entity = BlogSettings.builder()
                .id(1L)
                .logo(dto.getLogo())
                .name(dto.getName())
                .author(dto.getAuthor())
                .introduction(dto.getIntroduction())
                .avatar(dto.getAvatar())
                .githubHomepage(dto.getGithubHomePage())
                .juejinHomepage(dto.getJuejinHomePage())
                .wechatAccount(dto.getWechatAccount())
                .qqAccount(dto.getQqAccount())
                .build();
        // 保存或更新
        saveOrUpdate(entity);

        return Result.success();
    }

    /**
     * 获取博客设置详情
     * @return
     */
    @Override
    public Result queryBlogSettings() {
        // entity -> vo
        BlogSettings entity = this.baseMapper.selectById(1L);
        QueryBlogSettingsVO vo = QueryBlogSettingsVO.builder()
                .logo(entity.getLogo())
                .name(entity.getName())
                .author(entity.getAuthor())
                .introduction(entity.getIntroduction())
                .avatar(entity.getAvatar())
                .githubHomePage(entity.getGithubHomepage())
                .juejinHomePage(entity.getJuejinHomepage())
                .wechatAccount(entity.getWechatAccount())
                .qqAccount(entity.getQqAccount())
                .build();
        return Result.success(vo);
    }
}
