package com.lihainuo.bubulog.server.service.impl;

import com.lihainuo.bubulog.domain.entity.User;
import com.lihainuo.bubulog.repository.mapper.UserMapper;
import com.lihainuo.bubulog.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2025-05-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
