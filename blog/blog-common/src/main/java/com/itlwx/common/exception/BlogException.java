package com.itlwx.common.exception;

/**
 * 所有异常类基础类，所有异常必须继承此类
 * @author dawn
 *
 */
public class BlogException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String errorMsg;
	
	public BlogException(ErrorCode ecode){
		this.errorCode = ecode.getErrorCode();
		this.errorMsg = ecode.getErrorMsg();
	}
	
	public BlogException(String msg) {
		this.errorCode = ErrorCode.ERROR.getErrorCode();
		this.errorMsg = msg;
	}
	
	public BlogException(Throwable cause) {
		this.errorCode = ErrorCode.ERROR.getErrorCode();
		this.errorMsg = cause.getMessage();
	}
	
	public BlogException(String msg, Throwable cause) {
		this.errorCode = ErrorCode.ERROR.getErrorCode();
		this.errorMsg = msg;
	}
	
	@Override
	public String getMessage() {
		if (getCause() != null) {
			StringBuilder sb = new StringBuilder();
			if (super.getMessage() != null) {
				sb.append(super.getMessage()).append("; ");
			}
			sb.append("nested exception is ").append(getCause());
			return sb.toString();
		} else {
			return super.getMessage();
		}
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
