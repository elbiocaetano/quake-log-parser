package com.luizalabs.quake.logparser.exception;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 6757148585262076614L;

	private String developerMessage;

	private String userMessage;

	private Integer errorCode;

	public ErrorResponse() {

	}

	public ErrorResponse(String developerMessage, String userMessage, Integer errorCode) {
		super();
		this.developerMessage = developerMessage;
		this.userMessage = userMessage;
		this.errorCode = errorCode;

	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "ErrorResponse [developerMessage=" + developerMessage + ", userMessage=" + userMessage + ", errorCode="
				+ errorCode + "]";
	}

}
