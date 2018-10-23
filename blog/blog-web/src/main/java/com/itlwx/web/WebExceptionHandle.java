package com.itlwx.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itlwx.common.exception.BlogException;
import com.itlwx.common.exception.ErrorCode;
import com.itlwx.web.utils.HttpResult;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * 统一异常处理类
 * @author Tivan
 *
 */
public class WebExceptionHandle implements HandlerExceptionResolver, Ordered {
	protected static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandle.class);
	protected static final String AJAX_REQUEST_HEADER = "X-Requested-With";
	protected static final String AJAX_REQUEST_HEADER_VALUE = "XMLHttpRequest";
	protected static final String EXCEPTION = "exception";
	protected static final String ERROR_VIEW = "error";
	
	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object model, Exception ex) {
		try {
			HttpResult<Object> result = new HttpResult<Object>();
			if (ex instanceof BlogException) {
				result.setCode(((BlogException)ex).getErrorCode());
				result.setMsg(((BlogException)ex).getErrorMsg());
				//result.setMsg(I18nUtil.getMessage(String.valueOf(result.getCode())));
				//result.setData(ex.getMessage());
				//LOGGER.info(ex.getMessage());
			} else if(ex instanceof BindException){
				result.setCode(ErrorCode.PARAM_ERROR.getErrorCode());
				result.setMsg(ErrorCode.PARAM_ERROR.getErrorMsg());
				//result.setMsg(I18nUtil.getMessage(String.valueOf(result.getCode())));
				List<ObjectError> errors = ((BindException)ex).getAllErrors();
				List<String> msgs = new ArrayList<>();
				for(ObjectError error : errors){
					String field = BeanUtils.getProperty(error, "field");
					String msg = error.getDefaultMessage();
					msgs.add(field + msg);
					//LOGGER.info(error.getDefaultMessage());
				}
				result.setMsg(result.getMsg() + JSONObject.toJSONString(msgs));
				//result.setData(errorMsg);
			} else {
				result.setCode(ErrorCode.ERROR.getErrorCode());
				result.setMsg(ErrorCode.ERROR.getErrorMsg());
				//result.setMsg(I18nUtil.getMessage(String.valueOf(result.getCode())));
				result.setData(ex.getMessage());
				LOGGER.error("系统发生异常："+ ex.getMessage(), ex);
			}
			result.to(res);
		} catch (Exception e) {
			LOGGER.error("WebExceptionHandle-->resolveException返回错误视图失败", e);
		}
		return new ModelAndView();
	}

	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	}
	
}
