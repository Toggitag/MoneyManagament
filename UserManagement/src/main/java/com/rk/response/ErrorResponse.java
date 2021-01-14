package com.rk.response;

public class ErrorResponse {

	private String code;

	
	public ErrorResponse(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
