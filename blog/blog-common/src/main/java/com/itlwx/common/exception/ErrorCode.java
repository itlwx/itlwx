package com.itlwx.common.exception;

/**
 * <p>系统所有异常编码</p>
 * 规则：项目代号+模块编号+异常码<br/>
 * 项目代号两位：默认10，大数据平台<br/>
 * 模块编号两位：默认00，00系统级别｜01类别管理｜02文章管理｜03音频管理｜04视频管理｜05系统设置｜06公用<br/>
 * 异常编码三位：自定义<br/>
 * @author Tivan
 *
 */
public class ErrorCode {
	public static final ErrorCode SUCCESS = new ErrorCode(1000200, "操作成功");
	public static final ErrorCode ERROR = new ErrorCode(1000500, "系统错误");
	public static final ErrorCode TIMEOUT = new ErrorCode(1000408, "连接超时");
	public static final ErrorCode UN_AUTHORIZATION = new ErrorCode(1000403, "访问未授权");
	public static final ErrorCode PARAM_ERROR = new ErrorCode(1000401, "请求参数错误");
	public static final ErrorCode REMOTE_API_ERROR = new ErrorCode(1000405, "远程接口调用失败");
	public static final ErrorCode RES_NOT_FOUND = new ErrorCode(1000404, "资源不存在");
	public static final ErrorCode OK = new ErrorCode(200, "操作成功");


	/*公共*/
	public static final ErrorCode PUBLIC_RECORED_EXIST = new ErrorCode(1006001, "记录已存在");


	private int errorCode;
	private String errorMsg;
	
	public ErrorCode(){
		
	}
	
	public ErrorCode(int errorCode, String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
}
