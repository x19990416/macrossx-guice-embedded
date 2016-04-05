package com.macrossx.embedded;

public class EmbeddedServerException extends RuntimeException {

	private static final long serialVersionUID = -2913513709418945394L;
	public EmbeddedServerException(Exception e) {
		super(e);
	} 
	public EmbeddedServerException(String message, Throwable cause) {
		super(message, cause);
	}

}	