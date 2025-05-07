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
