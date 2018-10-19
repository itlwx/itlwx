package com.itlwx.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 配置文件属性获取器
 * @author dawn
 *
 */
public class PropertyGetter extends PropertyPlaceholderConfigurer {

	//configMap用于存放所有配置文件的属性与值
	private static Map<String, String> configMap = new HashMap<String, String>();
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		for(Object key: props.keySet()){
			System.out.println(key.toString() + "=" + props.getProperty(key.toString()));
			configMap.put(key.toString(), props.getProperty(key.toString()));
		}
	}
	
	/**
	 * 获取配置配置文件，不可以修改
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key){
		if(configMap.containsKey(key)){
			return configMap.get(key);
		}
		throw new IllegalArgumentException("no fonud config key : " + key);
	}
	
}
