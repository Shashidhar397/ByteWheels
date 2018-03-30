package com.bytewheels.app.exception;


public class BusinessException extends Exception{

	private int errorCode;
	
	public BusinessException(int errorCode, String message) {
		super(message);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	
	
}
