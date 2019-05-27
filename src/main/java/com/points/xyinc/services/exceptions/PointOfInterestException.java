package com.points.xyinc.services.exceptions;

public class PointOfInterestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PointOfInterestException(String msg) {
		super(msg);
	}

	public PointOfInterestException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
