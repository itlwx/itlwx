package com.itlwx.core.service;

import com.itlwx.core.bo.CategoryBO;

/**
 * 类别服务抽象接口
 * @author dawn
 */
public interface CategoryService {
    /**
     * 添加类别
     * @param categoryBO
     */
    void add(CategoryBO categoryBO);

    /**
     * 根据id删除类别
     * @param id
     */
    void deleteByID(Integer id);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    CategoryBO getByID(Integer id);

}
