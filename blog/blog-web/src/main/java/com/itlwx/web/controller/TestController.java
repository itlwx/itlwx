package com.itlwx.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/testIndex")
    public String testIndex(){
        return "index";
    }
}
