package com.lihainuo.bubulog.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文章分类表
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-22
 */
@Getter
@Setter
@Builder
@TableName("t_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标志位：0：未删除 1：已删除
     */
    private Byte isDeleted;
}
