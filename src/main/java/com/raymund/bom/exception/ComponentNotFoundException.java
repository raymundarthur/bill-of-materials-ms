package com.raymund.bom.exception;

/**
 * Custom exception thrown when component record is not found
 * @author Raymund
 *
 */
public class ComponentNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 162608294552088038L;

	public ComponentNotFoundException() {
		super();
	}

	public ComponentNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ComponentNotFoundException(final String message) {
		super(message);
	}

	public ComponentNotFoundException(final Throwable cause) {
		super(cause);
	}

}
