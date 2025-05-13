package com.lihainuo.bubulog.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lihainuo.bubulog.domain.entity.User;
import com.lihainuo.bubulog.domain.entity.UserRole;
import com.lihainuo.bubulog.repository.mapper.UserMapper;
import com.lihainuo.bubulog.repository.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @date 2025-05-08
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);

        // 暂时先写死，密码为 lihainuo, 这里填写的密文，数据库中也是存储此种格式
        // authorities 用于指定角色，这里写死为 ADMIN 管理员
        //  return User.withUsername("lihainuo")
        //          .password("$2a$10$IjEv0bk93lZw2J7W5HTAn.koNrUfABue1uh.rh0AkHslFB.tRW8KG")
        //          .authorities("ADMIN")
        //          .build();
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 查询用户的角色
        List<UserRole> role = userRoleMapper.selectByUserName(username);
        String[] rolesArr = null;
        // 转数组
        if (!CollectionUtils.isEmpty(role)) {
            List<String> roles = role.stream().map(UserRole::getRole).toList();
            rolesArr = roles.toArray(new String[roles.size()]);
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(rolesArr)
                .build();
    }
}

