package com.dc.job.portal.exception;

public class InvalidCredentialsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException(String errorMessage) {
		super(errorMessage);
	}

}
