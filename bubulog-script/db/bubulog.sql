-- 如果数据库存在，先删除（⚠️ 危险操作！会清空数据）
DROP DATABASE IF EXISTS `bubulog`;

-- 创建新数据库（指定字符集和排序规则）
CREATE DATABASE `bubulog` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 选择数据库
USE `bubulog`;

CREATE TABLE `t_user`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username`    varchar(60) NOT NULL COMMENT '用户名',
    `password`    varchar(60) NOT NULL COMMENT '密码',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除：0：未删除 1：已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `t_user_role` (
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username`    varchar(60) NOT NULL COMMENT '用户名',
    `role`        varchar(60) NOT NULL COMMENT '角色',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

CREATE TABLE `t_category` (
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `name`        varchar(60) NOT NULL DEFAULT '' COMMENT '分类名称',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志位：0：未删除 1：已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文章分类表';


-- 插入初始账号数据
INSERT INTO `t_user` (username, password)
VALUES ('admin', '$2a$10$LPqbsPETXNDbVQqYB1Jg3uj8PSq.8cW8T1jE0cQcQpN2YXza7C7Wi'),
       ('test', '$2a$10$AV3AyY1hvtuenqKp08ChMehrOG9wbwLfJwwDHkiFBlr9R6C3l8LX2');
-- 初始化账号对应角色数据
INSERT INTO `t_user_role` (id, username, role)
VALUES (1, 'admin', 'ROLE_ADMIN'),
       (2, 'test', 'ROLE_VISITOR');