package org.kosa.myproject.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7101581895552033605L;

	public AccountNotFoundException(String message) {
		super(message);
	}

}
