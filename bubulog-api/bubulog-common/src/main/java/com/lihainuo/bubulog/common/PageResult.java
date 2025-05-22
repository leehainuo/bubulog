package com.lihainuo.bubulog.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-22
 */

@Data
public class PageResult<T> extends Result<List<T>> {
    /**
     * 总记录数
     */
    private long total = 0L;

    /**
     * 每页显示的记录数，默认每页显示 10 条
     */
    private long size = 10L;

    /**
     * 当前页码
     */
    private long current;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 成功响应
     * @param page MyBatis Plus 提供的分页接口
     * @param data
     * @return
     */
    public static <T> PageResult<T> success(IPage page, List<T> data) {
        PageResult<T> res = new PageResult<>();
        res.setSuccess(true);
        res.setCurrent(Objects.isNull(page) ? 1L : page.getCurrent());
        res.setSize(Objects.isNull(page) ? 10L : page.getSize());
        res.setPages(Objects.isNull(page) ? 0L : page.getPages());
        res.setTotal(Objects.isNull(page) ? 0L : page.getTotal());
        res.setData(data);
        return res;
    }
}
