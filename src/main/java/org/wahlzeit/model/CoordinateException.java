package org.wahlzeit.model;

public class CoordinateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1636458993037564063L;

	public CoordinateException() {

	}

	public CoordinateException(String message) {
		super(message);
	}

	public CoordinateException(Throwable cause) {
		super(cause);
	}

	public CoordinateException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoordinateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
