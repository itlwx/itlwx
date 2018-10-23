package com.itlwx.core.service;

import com.itlwx.common.exception.CategoryException;
import com.itlwx.core.bo.CategoryBO;
import com.itlwx.core.bo.CategoryQueryBO;
import com.itlwx.core.bo.PageSet;

import java.util.List;

/**
 * 类别服务抽象接口
 * @author dawn
 */
public interface CategoryService {
    /**
     * 添加类别
     * @param categoryBO
     */
    void add(CategoryBO categoryBO) throws CategoryException;

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

    /**
     * 根据条件分页查询
     * @param categoryQueryBO
     * @return
     */
    PageSet<CategoryBO> query(CategoryQueryBO categoryQueryBO);

    /**
     * 编辑
     * @param cateBO
     */
    void edit(CategoryBO cateBO) throws CategoryException;

    /**
     * 根据类型获取分类列表
     * @param type
     * @return
     */
    List<CategoryBO> getListByType(Integer type);

}
