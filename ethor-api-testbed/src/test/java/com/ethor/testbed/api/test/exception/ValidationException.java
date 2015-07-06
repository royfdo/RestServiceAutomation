package com.ethor.testbed.api.test.exception;

import java.util.List;

/**
 * Exception class to encapsulate validation exceptions.
 * 
 * @author Roy Fernando.
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -3430087282496443124L;

	private final List<String> messages;

	public ValidationException(final String message) {
		super(message);
		this.messages = null;
	}

	public ValidationException(final List<String> messages) {
		super("Validation errors");
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

}
