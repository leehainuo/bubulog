package com.lihainuo.bubulog.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.lihainuo.bubulog.common.PageResult;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.enums.ResultEnum;
import com.lihainuo.bubulog.common.exception.BusinessException;
import com.lihainuo.bubulog.domain.dto.article.*;
import com.lihainuo.bubulog.domain.entity.*;
import com.lihainuo.bubulog.domain.vo.GetArticleDetailVO;
import com.lihainuo.bubulog.domain.vo.QueryArticleVO;
import com.lihainuo.bubulog.repository.mapper.*;
import com.lihainuo.bubulog.server.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2025-06-07
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    /**
     * 添加文章
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 事务注解
    public Result addArticle(AddArticleDTO dto) {
        // 1. dto -> entity(article) 并保存
        Article article = Article.builder()
                .title(dto.getTitle())
                .cover(dto.getCover())
                .summary(dto.getSummary())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        articleMapper.insert(article);
        // 2. dto -> entity(article_content)
        Long articleId = article.getId();
        ArticleContent articleContent = ArticleContent.builder()
                .articleId(articleId)
                .content(dto.getContent())
                .build();
        articleContentMapper.insert(articleContent);
        // 3. 处理文章关联的分类
        Long categoryId = dto.getCategoryId();
        Category category = categoryMapper.selectById(categoryId);
        if (Objects.isNull(category)) {
            log.warn("分类不存在, categoryId: {}", categoryId);
            throw new BusinessException(ResultEnum.CATEGORY_NAME_NOT_EXIST);
        }
        ArticleCategoryRel articleCategoryRel = ArticleCategoryRel.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRel);
        // 4. 保存文章标签
        List<String> tags = dto.getTags();
        insertTags(articleId, tags);

        return Result.success();
    }

    /**
     * 删除文章
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 事务注解
    public Result deleteArticle(DeleteArticleDTO dto) {
        Long articleId = dto.getArticleId();

        // 1. 删除文章
        articleMapper.deleteById(articleId);
        // 2. 删除文章内容
        articleContentMapper.deleteByArticleId(articleId);
        // 3. 删除文章-分类关联记录
        articleCategoryRelMapper.deleteByArticleId(articleId);
        // 4. 删除文章-标签关联记录
        articleTagRelMapper.deleteByArticleId(articleId);

        return Result.success();
    }

    /**
     * 更新文章
     * @param dto
     * @return
     */
    @Override
    public Result updateArticle(UpdateArticleDTO dto) {
        // 1. dto -> entity(Article)
        Long articleId = dto.getArticleId();
        Article article = Article.builder()
                .id(articleId)
                .title(dto.getArticleTitle())
                .cover(dto.getArticleCover())
                .summary(dto.getArticleSummary())
                .updateTime(new Date())
                .build();
        int res = articleMapper.updateById(article);
        /**
         * 根据文章是否更新成功，判断文章是否存在
         * res = 0 不存在 1 存在
         */
        if (res == 0) {
            log.warn("文章不存在, articleId: {}", articleId);
            throw new BusinessException(ResultEnum.ARTICLE_NOT_EXIST);
        }
        // 2. dto -> entity(ArticleContent)
        ArticleContent articleContent = ArticleContent.builder()
                .articleId(articleId)
                .content(dto.getArticleContent())
                .build();
        articleContentMapper.updateByArticleId(articleId, articleContent);
        // 3. 更新文章分类
        Long categoryId = dto.getCategoryId();
        // 3.1 校验提交的分类是否存在
        Category category = categoryMapper.selectById(categoryId);
        if (Objects.isNull(category)) {
            log.warn("分类不存在, categoryId: {}", categoryId);
            throw new BusinessException(ResultEnum.CATEGORY_NAME_NOT_EXIST);
        }
        // 3.2 先删除该文章的关联的分类记录，再插入新的关联记录
        articleCategoryRelMapper.deleteByArticleId(articleId);
        ArticleCategoryRel articleCategoryRel = ArticleCategoryRel.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRel);
        // 4. 保存文章关联的标签集合
        articleTagRelMapper.deleteByArticleId(articleId);
        List<String> tags = dto.getTags();
        insertTags(articleId, tags);

        return Result.success();
    }

    /**
     * 查询文章
     * @param dto
     * @return
     */
    @Override
    public Result queryArticle(QueryArticleDTO dto) {
        // 获取当前页、以及每页需要展示的数据数量
        Long current = dto.getCurrent();
        Long size = dto.getSize();
        String articleTitle = dto.getArticleTitle();
        Date startDate = dto.getStartDate();
        Date endDate = dto.getEndDate();

        // 执行分页查询
        Page<Article> page = articleMapper.selectByPage(current, size, articleTitle, startDate, endDate);
        List<Article> articles = page.getRecords();

        /**
         * entity -> vo
         * CollectionUtils.isEmpty() 判断是否为空 是:ture 否:false
         */
        List<QueryArticleVO> vos = null;
        if (!CollectionUtils.isEmpty(articles)) {
            vos = articles.stream()
                    .map(item -> QueryArticleVO.builder()
                            .articleId(item.getId())
                            .articleTitle(item.getTitle())
                            .articleCover(item.getCover())
                            .createDate(item.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return PageResult.success(page, vos);
    }

    /**
     * 获取文章详情
     * @param dto
     * @return
     */
    @Override
    public Result getArticleDetail(GetArticleDetailDTO dto) {
        Long articleId = dto.getArticleId();
        Article article = articleMapper.selectById(articleId);
        if (Objects.isNull(article)) {
            log.warn("查询的文章不存在, articleId: {}", articleId);
            throw new BusinessException(ResultEnum.ARTICLE_NOT_EXIST);
        }
        // 获取文章内容
        ArticleContent articleContent = articleContentMapper.selectById(articleId);
        // 获取文章所属分类
        ArticleCategoryRel articleCategoryRel = articleCategoryRelMapper.selectById(articleId);
        // 获取文章的对应标签集合
        List<ArticleTagRel> articleTagRels = articleTagRelMapper.selectByArticleId(articleId);
        List<Long> tagIds = articleTagRels.stream().map(ArticleTagRel::getTagId).collect(Collectors.toList());

        // entity -> vo
        GetArticleDetailVO vo = GetArticleDetailVO.builder()
                .articleId(articleId)
                .articleTitle(article.getTitle())
                .articleCover(article.getCover())
                .articleSummary(article.getSummary())
                .articleContent(articleContent.getContent())
                .categoryId(articleCategoryRel.getCategoryId())
                .tagIds(tagIds)
                .build();

        return Result.success(vo);
    }

    /**
     * 保存标签
     * @param articleId
     * @param tags
     */
    private void insertTags(Long articleId, List<String> tags) {
        // 筛选提交的标签（表中不存在的标签）
        List<String> notExistTags = null;
        // 筛选提交的标签（表中已存在的标签）
        List<String> existedTags = null;

        // 查询出所有标签
        List<Tag> entities = tagMapper.selectList(null);

        // 如果表中还没有添加任何标签
        if (CollectionUtils.isEmpty(entities)) {
            notExistTags = tags;
        } else {
            List<String> tagIds = entities.stream().map(tagDO -> String.valueOf(tagDO.getId())).collect(Collectors.toList());
            // 表中已添加相关标签，则需要筛选
            // 通过标签 ID 来筛选，包含对应 ID 则表示提交的标签是表中存在的
            existedTags = tags.stream().filter(tag -> tagIds.contains(tag)).collect(Collectors.toList());
            // 否则则是不存在的
            notExistTags = tags.stream().filter(tag -> !tagIds.contains(tag)).collect(Collectors.toList());

            // 补充逻辑：
            // 还有一种可能：按字符串名称提交上来的标签，也有可能是表中已存在的，比如表中已经有了 Java 标签，用户提交了个 java 小写的标签，需要内部装换为 Java 标签
            Map<String, Long> tagNameIdMap = entities.stream().collect(Collectors.toMap(tagDO -> tagDO.getName().toLowerCase(), Tag::getId));

            // 使用迭代器进行安全的删除操作
            Iterator<String> iterator = notExistTags.iterator();
            while (iterator.hasNext()) {
                String notExistTag = iterator.next();
                // 转小写, 若 Map 中相同的 key，则表示该新标签是重复标签
                if (tagNameIdMap.containsKey(notExistTag.toLowerCase())) {
                    // 从不存在的标签集合中清除
                    iterator.remove();
                    // 并将对应的 ID 添加到已存在的标签集合
                    existedTags.add(String.valueOf(tagNameIdMap.get(notExistTag.toLowerCase())));
                }
            }
        }

        // 将提交的上来的，已存在于表中的标签，文章-标签关联关系入库
        if (!CollectionUtils.isEmpty(existedTags)) {
            List<ArticleTagRel> articleTagRels = Lists.newArrayList();
            existedTags.forEach(tagId -> {
                ArticleTagRel articleTagRel = ArticleTagRel.builder()
                        .articleId(articleId)
                        .tagId(Long.valueOf(tagId))
                        .build();
                articleTagRels.add(articleTagRel);
            });
            // 批量插入
            articleTagRelMapper.insertBatchSomeColumn(articleTagRels);
        }

        // 将提交的上来的，不存在于表中的标签，入库保存
        if (!CollectionUtils.isEmpty(notExistTags)) {
            // 需要先将标签入库，拿到对应标签 ID 后，再把文章-标签关联关系入库
            List<ArticleTagRel> articleTagRels = Lists.newArrayList();
            notExistTags.forEach(tagName -> {
                Tag tag = Tag.builder()
                        .name(tagName)
                        .createTime(new Date())
                        .updateTime(new Date())
                        .build();

                tagMapper.insert(tag);

                // 拿到保存的标签 ID
                Long tagId = tag.getId();

                // 文章-标签关联关系
                ArticleTagRel articleTagRel = ArticleTagRel.builder()
                        .articleId(articleId)
                        .tagId(tagId)
                        .build();
                articleTagRels.add(articleTagRel);
            });
            // 批量插入
            articleTagRelMapper.insertBatchSomeColumn(articleTagRels);
        }
    }

}
