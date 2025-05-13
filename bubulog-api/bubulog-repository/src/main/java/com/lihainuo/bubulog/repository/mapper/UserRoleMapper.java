package com.lihainuo.bubulog.repository.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lihainuo.bubulog.domain.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-13
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    default List<UserRole> selectByUserName(String username) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUsername, username);
        return selectList(wrapper);
    }
}
