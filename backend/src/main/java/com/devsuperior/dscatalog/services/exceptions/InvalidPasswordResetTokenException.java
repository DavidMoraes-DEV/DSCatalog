package com.devsuperior.dscatalog.services.exceptions;

public class InvalidPasswordResetTokenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidPasswordResetTokenException(String msg) {
		super(msg);
	}
}
