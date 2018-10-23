package com.itlwx.web;

import com.itlwx.core.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    public static final String SESSION_USER = "SESSION_USER";

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 获取session中的user对象
     * @return
     */
    public UserBO getSessionUser(){
        return (UserBO) request.getSession().getAttribute(SESSION_USER);
    }

    /**
     * 将user对象放置到session中
     * @param user
     */
    public void setSessionUser(UserBO user){
        request.getSession().setAttribute(SESSION_USER,user);
    }
}
