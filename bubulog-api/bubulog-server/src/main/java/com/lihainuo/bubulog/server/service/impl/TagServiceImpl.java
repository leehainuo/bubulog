package com.lihainuo.bubulog.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lihainuo.bubulog.common.PageResult;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.enums.ResultEnum;
import com.lihainuo.bubulog.common.exception.BusinessException;
import com.lihainuo.bubulog.domain.dto.tag.AddTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.DeleteTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.QueryTagDTO;
import com.lihainuo.bubulog.domain.dto.tag.UpdateTagDTO;
import com.lihainuo.bubulog.domain.entity.Tag;

import com.lihainuo.bubulog.domain.vo.QueryTagVO;
import com.lihainuo.bubulog.repository.mapper.TagMapper;
import com.lihainuo.bubulog.server.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-27
 */

@Slf4j
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加标签
     * @param dto
     * @return
     */
    @Override
    public Result addTag(AddTagDTO dto) {
        String tagName = dto.getTagName();
        // 先判断该标签是否存在
        Tag tag = tagMapper.selectByName(tagName);

        if (Objects.nonNull(tag)) {
            log.warn("标签名称：{}，已存在", tagName);
            throw new BusinessException(ResultEnum.CATEGORY_NAME_EXIST);
        }

        // 不存在则添加
        Tag newTag = Tag.builder()
                .name(tagName.trim())
                .build();

        tagMapper.insert(newTag);

        return Result.success();
    }

    /**
     * 删除标签
     * @param dto
     * @return
     */
    @Override
    public Result deleteTag(DeleteTagDTO dto) {
        Long tagId = dto.getTagId();
        tagMapper.deleteById(tagId);
        return Result.success();
    }

    /**
     * 更新标签
     * @param dto
     * @return
     */
    @Override
    public Result updateTag(UpdateTagDTO dto) {
        // 获取更新标签Id、名称
        Long tagId = dto.getTagId();
        String tagName = dto.getTagName();

        // 构建更新对象
        Tag tag = Tag.builder()
                .id(tagId)
                .name(tagName)
                .updateTime(new Date())
                .build();
        tagMapper.updateById(tag);

        return Result.success();
    }

    /**
     * 查询标签
     * @param dto
     * @return
     */
    @Override
    public PageResult queryTag(QueryTagDTO dto) {
        // 获取当前页、以及每页需要展示的数量
        Long current = dto.getCurrent();
        Long size = dto.getSize();
        // 分页对象
        Page<Tag> page = new Page<>(current, size);

        // 构建分页查询
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        String tagName = dto.getTagName();
        Date startDate = dto.getStartDate();
        Date endDate = dto.getEndDate();

        wrapper
            .like(StringUtils.isNotBlank(tagName), Tag::getName, tagName.trim())
            .ge(Objects.nonNull(startDate), Tag::getCreateTime, startDate)
            .le(Objects.nonNull(endDate), Tag::getCreateTime, endDate)
            .orderByDesc(Tag::getCreateTime);

        // 执行分页查询
        Page<Tag> tagPage = tagMapper.selectPage(page, wrapper);

        List<Tag> tags = tagPage.getRecords();
        log.info("----{}----", tags);
        List<QueryTagVO> vos = null;
        if(!CollectionUtils.isEmpty(tags)) {
            vos = tags.stream()
                    .map(item -> QueryTagVO.builder()
                            .tagId(item.getId())
                            .tagName(item.getName())
                            .createDate(item.getCreateTime())
                            .build()
                    )
                    .collect(Collectors.toList());
        }

        return PageResult.success(tagPage, vos);
    }

}
