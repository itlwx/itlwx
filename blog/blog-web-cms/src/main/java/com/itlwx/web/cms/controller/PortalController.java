package com.itlwx.web.cms.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class PortalController {

    @RequestMapping(value = "/toPortal")
    public String testIndex(){
        return "index";
    }
}
