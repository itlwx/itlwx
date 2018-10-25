package com.itlwx.core.service;

import com.itlwx.common.exception.ArticleException;
import com.itlwx.core.bo.ArticleBO;
import com.itlwx.core.bo.ArticleQueryBO;
import com.itlwx.core.bo.PageSet;

public interface ArticleService {
    /**
     * 添加文章
     * @param articleBO
     * @throws ArticleException
     */
    void add(ArticleBO articleBO) throws ArticleException;

    /**
     * 删除文章
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新文章
     * @param articleBO
     * @throws ArticleException
     */
    void update(ArticleBO articleBO) throws ArticleException;

    /**
     * 查询文章
     * @param queryBO
     * @return
     */
    PageSet<ArticleBO> query(ArticleQueryBO queryBO);
}
