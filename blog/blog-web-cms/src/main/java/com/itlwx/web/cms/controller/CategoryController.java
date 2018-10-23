package com.itlwx.web.cms.controller;
import com.itlwx.common.exception.CategoryException;
import com.itlwx.core.bo.CategoryBO;
import com.itlwx.core.bo.CategoryQueryBO;
import com.itlwx.core.bo.PageSet;
import com.itlwx.core.service.CategoryService;
import com.itlwx.web.BaseController;
import com.itlwx.web.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询
     * @param queryBO
     * @param mav
     * @return
     */
    @RequestMapping(value = "/query")
    public ModelAndView query(CategoryQueryBO queryBO,ModelAndView mav){
        PageSet<CategoryBO> pageSet = categoryService.query(queryBO);
        mav.setViewName("cate");
        mav.addObject("ps",pageSet);

        return mav;
    }


    @RequestMapping(value = "/showCateadd")
    public String showCateadd(){
        return "cateadd";
    }

    /**
     * 添加
     * @param categoryBO
     * @return
     * @throws CategoryException
     */
    @RequestMapping(value = "/cateadd")
    public ModelAndView cateadd(@Validated CategoryBO categoryBO) throws CategoryException {
        categoryService.add(categoryBO);
        HttpResult.toSuccess(getResponse());
        return null;
    }

    @RequestMapping(value = "/showedit")
    public ModelAndView showedit(CategoryBO categoryBO,ModelAndView mav){
        CategoryBO cbo = categoryService.getByID(categoryBO.getId());
        mav.setViewName("cateedit");
        mav.addObject("item",cbo);
        return mav;
    }

    /**
     * 编辑
     * @param categoryBO
     * @return
     * @throws CategoryException
     */
    @RequestMapping(value = "/cateedit")
    public ModelAndView cateedit(CategoryBO categoryBO) throws CategoryException {
        categoryService.edit(categoryBO);
        HttpResult.toSuccess(getResponse());
        return null;
    }

    /**
     * 删除
     * @param categoryBO
     * @return
     */
    @RequestMapping(value = "/catedel")
    public String catedel(CategoryBO categoryBO){
        categoryService.deleteByID(categoryBO.getId());
        return "forward:/category/query.htm";
    }

}
