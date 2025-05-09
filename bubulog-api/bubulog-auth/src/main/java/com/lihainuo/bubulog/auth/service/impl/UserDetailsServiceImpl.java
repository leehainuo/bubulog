package com.lihainuo.bubulog.auth.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @date 2025-05-08
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询
        // ...

        // 暂时先写死，密码为 lihainuo, 这里填写的密文，数据库中也是存储此种格式
        // authorities 用于指定角色，这里写死为 ADMIN 管理员
        return User.withUsername("lihainuo")
                .password("$2a$10$IjEv0bk93lZw2J7W5HTAn.koNrUfABue1uh.rh0AkHslFB.tRW8KG")
                .authorities("ADMIN")
                .build();
    }
}

