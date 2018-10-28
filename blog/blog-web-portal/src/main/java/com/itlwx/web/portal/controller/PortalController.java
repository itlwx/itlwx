package com.itlwx.web.portal.controller;
import com.itlwx.core.bo.ArticleBO;
import com.itlwx.core.bo.ArticleQueryBO;
import com.itlwx.core.bo.PageSet;
import com.itlwx.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class PortalController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/index")
    public ModelAndView showIndex(ArticleQueryBO queryBO){
        PageSet<ArticleBO> pageSet = articleService.query(queryBO);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("ps",pageSet);
        return mav;
    }

    @RequestMapping(value = "/showArticle")
    public ModelAndView showArticle(ArticleBO articleBO){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("articleDetail");

        return mav;
    }
}
