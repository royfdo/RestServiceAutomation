package com.ethor.testbed.api.test.exception;

/**
 * Exception class to encapsulate API response exceptions.
 * 
 * @author Roy Fernando.
 */
public class ResponseException extends RuntimeException {

	private static final long serialVersionUID = 7212026405107011482L;

	private final String code;

	public ResponseException(final String message) {
		super(message);
		this.code = null;
	}

	public ResponseException(final String message, final String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
