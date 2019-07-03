package com.tradetask.controller.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private HttpStatus error;
	private String message;

	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}