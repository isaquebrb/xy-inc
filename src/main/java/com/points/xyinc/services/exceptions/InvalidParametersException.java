package com.points.xyinc.services.exceptions;

public class InvalidParametersException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidParametersException(String msg) {
		super(msg);
	}
	
	public InvalidParametersException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
