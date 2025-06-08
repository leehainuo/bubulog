package com.lihainuo.bubulog.server.service.impl;


import com.lihainuo.bubulog.domain.entity.ArticleTagRel;
import com.lihainuo.bubulog.repository.mapper.ArticleTagRelMapper;
import com.lihainuo.bubulog.server.service.ArticleTagRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章对应标签关联表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
@Service
public class ArticleTagRelServiceImpl extends ServiceImpl<ArticleTagRelMapper, ArticleTagRel> implements ArticleTagRelService {

}
