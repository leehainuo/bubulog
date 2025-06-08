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

CREATE TABLE `t_tag` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
    `name` varchar(60) NOT NULL DEFAULT '' COMMENT '标签名称',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志位：0：未删除 1：已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文章标签表';

CREATE TABLE `t_blog_settings` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `logo` varchar(120) NOT NULL DEFAULT '' COMMENT '博客Logo',
    `name` varchar(60) NOT NULL DEFAULT '' COMMENT '博客名称',
    `author` varchar(20) NOT NULL DEFAULT '' COMMENT '作者名',
    `introduction` varchar(120) NOT NULL DEFAULT '' COMMENT '介绍语',
    `avatar` varchar(120) NOT NULL DEFAULT '' COMMENT '作者头像',
    `github_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT 'GitHub 主页访问地址',
    `juejin_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT '稀土掘金 主页访问地址',
    `wechat_account` varchar(60) NOT NULL DEFAULT '' COMMENT '微信号',
    `qq_account` varchar(60) NOT NULL DEFAULT '' COMMENT 'QQ号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='博客设置表';

CREATE TABLE `t_article` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章id',
    `title` varchar(120) NOT NULL DEFAULT '' COMMENT '文章标题',
    `cover` varchar(120) NOT NULL DEFAULT '' COMMENT '文章封面',
    `summary` varchar(160) DEFAULT '' COMMENT '文章摘要',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
    `read_num` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '被阅读次数',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文章表';

CREATE TABLE `t_article_content` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章内容id',
    `article_id` bigint(20) NOT NULL COMMENT '文章id',
    `content` text COMMENT '教程正文',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_article_id` (`article_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文章内容表';

CREATE TABLE `t_article_category_rel` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `article_id` bigint(20) unsigned NOT NULL COMMENT '文章id',
    `category_id` bigint(20) unsigned NOT NULL COMMENT '分类id',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uni_article_id` (`article_id`) USING BTREE,
    KEY `idx_category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文章所属分类关联表';

CREATE TABLE `t_article_tag_rel` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `article_id` bigint(20) unsigned NOT NULL COMMENT '文章id',
    `tag_id` bigint(20) unsigned NOT NULL COMMENT '标签id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_article_id` (`article_id`) USING BTREE,
    KEY `idx_tag_id` (`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文章对应标签关联表';


-- 插入初始账号数据
INSERT INTO `t_user` (username, password)
VALUES ('admin', '$2a$10$LPqbsPETXNDbVQqYB1Jg3uj8PSq.8cW8T1jE0cQcQpN2YXza7C7Wi'),
       ('test', '$2a$10$AV3AyY1hvtuenqKp08ChMehrOG9wbwLfJwwDHkiFBlr9R6C3l8LX2');
-- 初始化账号对应角色数据
INSERT INTO `t_user_role` (id, username, role)
VALUES (1, 'admin', 'ROLE_ADMIN'),
       (2, 'test', 'ROLE_VISITOR');