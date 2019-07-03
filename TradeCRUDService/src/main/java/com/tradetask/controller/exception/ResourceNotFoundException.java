package com.tradetask.controller.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String string) {
		super(string);
	}

	public ResourceNotFoundException(String string, Throwable throwable) {
	}

	public ResourceNotFoundException(String string, int id) {
		super(string);
	}

}
