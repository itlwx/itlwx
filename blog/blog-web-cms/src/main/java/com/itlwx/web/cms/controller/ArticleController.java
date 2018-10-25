package com.itlwx.web.cms.controller;

import com.itlwx.core.bo.ArticleBO;
import com.itlwx.core.bo.CategoryBO;
import com.itlwx.core.bo.CategoryQueryBO;
import com.itlwx.core.bo.PageSet;
import com.itlwx.core.service.CategoryService;
import com.itlwx.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {


    @Autowired
    private CategoryService cateService;

    @RequestMapping(value = "/list")
    public String showList(){
        return "articlelist";
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
    public String add(@Validated ArticleBO articleBO){
        articleBO.setAuthroId(getSessionUser().getId());

        return "articlelist";
    }
}
