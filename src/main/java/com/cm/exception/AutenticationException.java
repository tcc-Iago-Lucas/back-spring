package com.cm.exception;

public class AutenticationException extends RuntimeException {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AutenticationException(String message) {
        super(message);
    }

    public AutenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
