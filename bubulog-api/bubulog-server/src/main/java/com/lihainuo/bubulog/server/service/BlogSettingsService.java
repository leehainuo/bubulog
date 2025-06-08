package com.lihainuo.bubulog.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.dto.UpdateBlogSettingsDTO;
import com.lihainuo.bubulog.domain.entity.BlogSettings;

/**
 * <p>
 * 博客设置表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-03
 */
public interface BlogSettingsService extends IService<BlogSettings> {

    /**
     * 更新博客设置
     * @param dto
     * @return
     */
    Result updateBlogSettings(UpdateBlogSettingsDTO dto);

    /**
     * 获取博客设置详情
     * @return
     */
    Result queryBlogSettings();
}
