package com.itlwx.web.cms.controller;

import com.itlwx.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {
    @RequestMapping(value = "/list")
    public String showCateadd(){
        return "articlelist";
    }
}
