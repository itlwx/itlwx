package com.itlwx.web.utils;

import com.itlwx.common.exception.ErrorCode;
import com.itlwx.common.utils.MapperUtil;

import javax.servlet.http.HttpServletResponse;

public class HttpResult <T> {
	private int code;
	private String msg;
	private T data;
	
	public HttpResult(){
		this.code = ErrorCode.SUCCESS.getErrorCode();
		this.msg = ErrorCode.SUCCESS.getErrorMsg();
	}
	
	public HttpResult(T data){
		this.code = ErrorCode.SUCCESS.getErrorCode();
		this.msg = ErrorCode.SUCCESS.getErrorMsg();
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public static void toError(ErrorCode ecode, HttpServletResponse response){
		HttpResult<Object> result = new HttpResult<>();
		result.setCode(ecode.getErrorCode());
		result.setMsg(ecode.getErrorMsg());
		result.to(response);
	}
	public static void toError(HttpServletResponse response){
		HttpResult<Object> result = new HttpResult<>();
		result.setCode(ErrorCode.ERROR.getErrorCode());
		result.setMsg(ErrorCode.ERROR.getErrorMsg());
		result.to(response);
	}
	public static void toSuccess(HttpServletResponse response){
		HttpResult<Object> result = new HttpResult<>();
		result.setCode(ErrorCode.SUCCESS.getErrorCode());
		result.setMsg(ErrorCode.SUCCESS.getErrorMsg());
		result.to(response);
	}
	public void to(HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(ErrorCode.OK.getErrorCode());
		//setMsg(I18nUtil.getMessage(String.valueOf(getCode())));
		String jsonStr = MapperUtil.toJsonStr(this);
		try{
            response.getWriter().write(jsonStr);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	public static void to(HttpServletResponse response,Object obj){
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(ErrorCode.OK.getErrorCode());
		String jsonStr = MapperUtil.toJsonStr(obj);
		try{
            response.getWriter().write(jsonStr);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
