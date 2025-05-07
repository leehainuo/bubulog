package com.lihainuo.bubulog.repository.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lihainuo.bubulog.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
