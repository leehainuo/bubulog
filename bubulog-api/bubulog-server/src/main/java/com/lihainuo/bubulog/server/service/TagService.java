package com.lihainuo.bubulog.server.service;

import com.lihainuo.bubulog.common.PageResult;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.dto.tag.AddTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.DeleteTagDTO;
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
     * @param dto
     * @return
     */
    Result addTag(AddTagDTO dto);

    /**
     * 删除标签
     * @param dto
     * @return
     */
    Result deleteTag(DeleteTagDTO dto);

    /**
     * 更新标签
     * @param dto
     * @return
     */
    Result updateTag(UpdateTagDTO dto);

    /**
     * 查询标签
     * @param dto
     * @return
     */
    PageResult queryTag(QueryTagDTO dto);

    /**
     * 下拉列表获取标签
     * @return
     */
    Result selectListTag();

    /**
     * 前台获取标签列表
     * @return
     */
    Result queryTagList();
}
