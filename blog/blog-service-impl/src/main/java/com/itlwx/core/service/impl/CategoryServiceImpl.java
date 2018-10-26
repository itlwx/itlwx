package com.itlwx.core.service.impl;

import com.itlwx.common.exception.CategoryException;
import com.itlwx.common.exception.ErrorCode;
import com.itlwx.common.utils.MapperUtil;
import com.itlwx.core.bean.Article;
import com.itlwx.core.bean.ArticleExample;
import com.itlwx.core.bean.Category;
import com.itlwx.core.bean.CategoryExample;
import com.itlwx.core.bo.CategoryBO;
import com.itlwx.core.bo.CategoryQueryBO;
import com.itlwx.core.bo.PageSet;
import com.itlwx.core.mapper.ArticleMapper;
import com.itlwx.core.mapper.CategoryMapper;
import com.itlwx.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(CategoryBO categoryBO) throws CategoryException{
        //根据类别名称与类别类型查询记录
        CategoryExample example = new CategoryExample();
        example.createCriteria()
            .andTypeEqualTo(categoryBO.getType())
            .andNameEqualTo(categoryBO.getName());

        List<Category> categories = categoryMapper.selectByExample(example);

        if(categories != null && categories.size() > 0){
           //若同类型下有相同的类别，抛出异常
           throw new CategoryException(ErrorCode.PUBLIC_RECORED_EXIST);
        }

        Category category = MapperUtil.map(categoryBO, Category.class);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        categoryMapper.insert(category);
    }

    @Override
    public void deleteByID(Integer id) throws CategoryException{
        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andCategoryIdEqualTo(id)
                .andDeletedEqualTo(1);

        List<Article> articles = articleMapper.selectByExample(example);

        if (articles != null && articles.size() > 0) {
            throw new CategoryException(ErrorCode.CATEGORY_NOT_DELETE);
        }

        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CategoryBO getByID(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        CategoryBO categoryBO = MapperUtil.map(category, CategoryBO.class);
        return categoryBO;
    }

    @Override
    public PageSet<CategoryBO> query(CategoryQueryBO categoryQueryBO) {

        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();

        if (StringUtils.hasText(categoryQueryBO.getName())){
            criteria.andNameEqualTo(categoryQueryBO.getName());
        }

        if (categoryQueryBO.getType() != null && !categoryQueryBO.getType().equals(0)) {
            criteria.andTypeEqualTo(categoryQueryBO.getType());
        }

        /*设置分页*/
        example.setLimitEnd(categoryQueryBO.getLimitEnd());
        example.setLimitStart(categoryQueryBO.getLimitStart());


        int count = categoryMapper.countByExample(example);
        PageSet<CategoryBO> cateps  = new PageSet<>(categoryQueryBO.getCurrentPage(),categoryQueryBO.getPageOfSize(),count);
        if (count > 0) {
            List<Category> categoryList = categoryMapper.selectByExample(example);
            List<CategoryBO> categories = MapperUtil.map(categoryList, CategoryBO.class);
            cateps.setItems(categories);
            return cateps;
        }

        return null;
    }

    @Override
    public void edit(CategoryBO cateBO) throws CategoryException {

        //根据类别名称与类别类型查询记录
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(cateBO.getType());
        criteria.andNameEqualTo(cateBO.getName());

        List<Category> categories = categoryMapper.selectByExample(example);

        if(categories != null && categories.size() > 0){
            Category category = categories.get(0);
            if (!category.getId().equals(cateBO.getId())) {
                //若同类型下有相同的类别，抛出异常
                throw new CategoryException(ErrorCode.PUBLIC_RECORED_EXIST);
            }

        }

        Category category = MapperUtil.map(cateBO, Category.class);
        category.setUpdateTime(new Date());
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public List<CategoryBO> getListByType(Integer type) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andTypeEqualTo(type);
        List<Category> categories = categoryMapper.selectByExample(example);
        if (categories != null && categories.size() > 0){
            return MapperUtil.map(categories,CategoryBO.class);
        }
        return null;
    }

}
