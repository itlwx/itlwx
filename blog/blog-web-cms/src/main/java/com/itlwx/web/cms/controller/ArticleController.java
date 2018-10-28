package com.itlwx.web.cms.controller;

import com.itlwx.common.exception.ArticleException;
import com.itlwx.common.exception.ErrorCode;
import com.itlwx.common.valid.GroupAll;
import com.itlwx.core.bo.*;
import com.itlwx.core.service.ArticleService;
import com.itlwx.core.service.CategoryService;
import com.itlwx.web.BaseController;
import com.itlwx.web.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService cateService;

    @RequestMapping(value = "/query")
    public ModelAndView query(ArticleQueryBO queryBO){
        PageSet<ArticleBO> ps = articleService.query(queryBO);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("articlelist");
        mav.addObject("ps",ps);
        return mav;
    }

    @RequestMapping(value = "/showAdd")
    public ModelAndView showAdd(){
        ModelAndView mav = new ModelAndView();
        CategoryQueryBO cateQueryBO = new CategoryQueryBO();
        cateQueryBO.setType(CategoryService.TYPE_ARTICLE);
        PageSet<CategoryBO> pageSet = cateService.query(cateQueryBO);
        mav.setViewName("articleadd");
        mav.addObject("items",pageSet.getItems());
        return mav;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@Validated ArticleBO articleBO) throws ArticleException {
        articleBO.setAuthroId(getSessionUser().getId());
        articleService.add(articleBO);
        HttpResult.toSuccess(getResponse());
        return null;
    }

    @RequestMapping(value = "/showEdit")
    public ModelAndView showEdit(Integer id) throws ArticleException{
        ModelAndView mav = new ModelAndView();
        //加载文章记录
        ArticleBO articleBO = articleService.getById(id);

        //加载文章类别数据
        CategoryQueryBO cateQueryBO = new CategoryQueryBO();
        cateQueryBO.setType(CategoryService.TYPE_ARTICLE);
        PageSet<CategoryBO> pageSet = cateService.query(cateQueryBO);


        mav.setViewName("articleedit");
        mav.addObject("item",articleBO);
        mav.addObject("categrys",pageSet.getItems());
        return mav;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(@Validated(GroupAll.class) ArticleBO articleBO) throws ArticleException {
        articleBO.setAuthroId(getSessionUser().getId());
        articleService.update(articleBO);
        HttpResult.toSuccess(getResponse());
        return null;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(ArticleBO articleBO){
        articleService.delete(articleBO.getId());
        HttpResult.toSuccess(getResponse());
        return null;
    }
}
