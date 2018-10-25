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

import java.util.Date;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(ArticleBO articleBO) throws ArticleException {
        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andTitleEqualTo(articleBO.getTitle())
                .andCategoryIdEqualTo(articleBO.getCategoryId());
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
        Article article = MapperUtil.map(articleBO, Article.class);
        article.setUpdateTime(new Date());
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public PageSet<ArticleBO> query(ArticleQueryBO queryBO) {
        return null;
    }
}
