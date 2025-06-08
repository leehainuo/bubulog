package com.lihainuo.bubulog.server.service.impl;


import com.lihainuo.bubulog.domain.entity.ArticleContent;
import com.lihainuo.bubulog.repository.mapper.ArticleContentMapper;
import com.lihainuo.bubulog.server.service.ArticleContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章内容表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
@Service
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent> implements ArticleContentService {

}
