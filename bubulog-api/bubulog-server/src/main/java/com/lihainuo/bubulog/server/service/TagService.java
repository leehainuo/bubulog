package com.lihainuo.bubulog.server.service;

import com.lihainuo.bubulog.common.PageResult;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.dto.tag.AddTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.DeleteDTO;
import com.lihainuo.bubulog.domain.dto.tag.QueryTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.UpdateTagDTO;
import com.lihainuo.bubulog.domain.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章标签表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-27
 */
public interface TagService extends IService<Tag> {
    /**
     * 添加标签
     * @param addTagDTO
     * @return
     */
    Result addTag(AddTagDTO addTagDTO);

    /**
     * 删除标签
     * @param deleteDTO
     * @return
     */
    Result deleteTag(DeleteDTO deleteDTO);

    /**
     * 更新标签
     * @param updateTagDTO
     * @return
     */
    Result updateTag(UpdateTagDTO updateTagDTO);

    /**
     * 查询标签
     * @param queryTagDTO
     * @return
     */
    PageResult queryTag(QueryTagDTO queryTagDTO);
}
