package com.itlwx.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class MapperUtil {
	private static final Logger log = LoggerFactory.getLogger(MapperUtil.class);
	
	public static <T> List<T> map(List<?> objects, Class<T> clzz){
		List<T> result = null;
		if(objects != null && objects.size() > 0){
			result = new ArrayList<T>();
			for(Object object : objects){
				result.add(map(object, clzz));
			}
		}
		return result;
	}
	
	public static <T> T map(Object object, Class<T> clzz){
		if(object != null){
			return ObjectMapperFactory.beanMapper.map(object, clzz);
		}
		return null;
	}
	
	public static String toJsonStr(Object object){
		try {
			return ObjectMapperFactory.jsonMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("对象转换json字符失败", e);
		}
		return null;
	}
	
	public static byte[] toJsonByte(Object object){
		try {
			return ObjectMapperFactory.jsonMapper.writeValueAsBytes(object);
		} catch (JsonProcessingException e) {
			log.error("对象转换json字符失败", e);
		}
		return null;
	}
	
	public static <T> T toObject(String jsonStr, Class<T> clazz){
		try {
			return ObjectMapperFactory.jsonMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			log.error("json字符转换对象失败", e);
		} 
		return null;
	}
	
	public static <T> T toObject(byte[] jsonStr, Class<T> clazz){
		try {
			return ObjectMapperFactory.jsonMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			log.error("json字符转换对象失败", e);
		} 
		return null;
	}
	
	private static class ObjectMapperFactory{
		public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		public static final ObjectMapper jsonMapper;
		public static final ObjectMapper xmlMapper;
		public static final Mapper beanMapper;
		static{
			jsonMapper = new ObjectMapper();
			jsonMapper.setDateFormat(dateFormat);
			jsonMapper.setSerializationInclusion(Include.NON_EMPTY);
			jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			xmlMapper = new XmlMapper();
			xmlMapper.setDateFormat(dateFormat);
			xmlMapper.setSerializationInclusion(Include.NON_EMPTY);
			xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			beanMapper = SpringContextUtil.getBean(Mapper.class);
		}
	}
	
	public static ObjectMapper getJsonMapper(){
		return ObjectMapperFactory.jsonMapper;
	}
	
	public static ObjectMapper getXmlMapper(){
		return ObjectMapperFactory.xmlMapper;
	}
	
	public static Mapper getBeanMapper(){
		return ObjectMapperFactory.beanMapper;
	}
	
}
