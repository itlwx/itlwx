package com.itlwx.web.cms.controller;
import com.itlwx.common.exception.CategoryException;
import com.itlwx.core.bo.CategoryBO;
import com.itlwx.core.bo.CategoryQueryBO;
import com.itlwx.core.bo.PageSet;
import com.itlwx.core.service.CategoryService;
import com.itlwx.web.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/query")
    public ModelAndView query(CategoryQueryBO queryBO,ModelAndView mav,boolean forwar){

        /*如果为请求转发，做特殊处理*/
        if(forwar){
            queryBO.setName(null);
            queryBO.setType(null);
        }

        PageSet<CategoryBO> pageSet = categoryService.query(queryBO);
        mav.setViewName("cate");
        mav.addObject("ps",pageSet);
        return mav;
    }

    @RequestMapping(value = "/showCateadd")
    public String toIndex(){
        return "cateadd";
    }

    @RequestMapping(value = "/cateadd")
    public String cateadd(@Validated CategoryBO categoryBO, HttpServletResponse response) throws CategoryException {
        categoryService.add(categoryBO);
        HttpResult.toSuccess(response);
        return null;
    }

    @RequestMapping(value = "/showedit")
    public ModelAndView showedit(CategoryBO categoryBO,ModelAndView mav){
        CategoryBO cbo = categoryService.getByID(categoryBO.getId());
        mav.setViewName("cateedit");
        mav.addObject("item",cbo);
        return mav;
    }

    @RequestMapping(value = "/cateedit")
    public String cateedit(CategoryBO categoryBO) throws CategoryException {
        categoryService.edit(categoryBO);
        return "forward: /query.htm?forwar=true";
    }


}
