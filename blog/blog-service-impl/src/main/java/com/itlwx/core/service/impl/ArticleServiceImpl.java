package com.itlwx.core.service.impl;

import com.itlwx.common.exception.ArticleException;
import com.itlwx.common.exception.ErrorCode;
import com.itlwx.common.utils.MapperUtil;
import com.itlwx.core.bean.Article;
import com.itlwx.core.bean.ArticleExample;
import com.itlwx.core.bo.ArticleBO;
import com.itlwx.core.bo.ArticleQueryBO;
import com.itlwx.core.bo.PageSet;
import com.itlwx.core.mapper.ArticleMapper;
import com.itlwx.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(ArticleBO articleBO) throws ArticleException {
        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andTitleEqualTo(articleBO.getTitle())
                .andCategoryIdEqualTo(articleBO.getCategoryId())
                .andDeletedEqualTo(1);
        List<Article> articleList = articleMapper.selectByExample(example);

        //若存在同样标题的文章则抛出异常
        if(articleList != null && articleList.size() > 0){
            throw new ArticleException(ErrorCode.PUBLIC_RECORED_EXIST);
        }

        Article article = MapperUtil.map(articleBO, Article.class);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setHits(0);
        article.setPostNum(0);
        articleMapper.insertSelective(article);
    }

    @Override
    public void delete(Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setDeleted(2);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void update(ArticleBO articleBO) throws ArticleException {
        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andTitleEqualTo(articleBO.getTitle())
                .andCategoryIdEqualTo(articleBO.getCategoryId())
                .andIdNotEqualTo(articleBO.getId())
                .andDeletedEqualTo(1);
        List<Article> articles = articleMapper.selectByExample(example);
        if (articles != null && articles.size() > 0) {
            throw new ArticleException(ErrorCode.ARTICLE_TITLE_REPEATED);
        }

        Article article = MapperUtil.map(articleBO, Article.class);
        article.setUpdateTime(new Date());
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public PageSet<ArticleBO> query(ArticleQueryBO queryBO) {
        int count = articleMapper.count4Query(queryBO);
        PageSet<ArticleBO> ps = new PageSet<>(queryBO.getCurrentPage(),queryBO.getPageOfSize(),count);
        if(count > 0){
            List<Article> articles = articleMapper.query(queryBO);
            ps.setItems(MapperUtil.map(articles,ArticleBO.class));
        }
        return ps;
    }

    @Override
    public ArticleBO getById(Integer id) throws ArticleException{

        if(id == null){
            throw  new ArticleException(ErrorCode.PUBLIC_ID_NOTNULL);
        }


        Article article = articleMapper.selectByPrimaryKey(id);

        if(article == null){
            throw  new ArticleException(ErrorCode.PUBLIC_RECORED_NOTEXIST);
        }
        return MapperUtil.map(article,ArticleBO.class);
    }
}
