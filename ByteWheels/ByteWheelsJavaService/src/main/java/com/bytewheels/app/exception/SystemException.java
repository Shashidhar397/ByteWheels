package com.bytewheels.app.exception;

public class SystemException extends Exception{

	private int errorCode;
	
	public SystemException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	
	
}
