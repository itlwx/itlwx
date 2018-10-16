package com.itlwx.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring容器工具类
 * @author dawn
 */

public class SpringContextUtil implements ApplicationContextAware {
	public static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}
	
	public static <T> T getBean(Class<T> requiredType){
		return applicationContext.getBean(requiredType);
	}
	
		
	public static <T> T getBean(String name, Class<T> requiredType){
		return applicationContext.getBean(name, requiredType);
	}

}
