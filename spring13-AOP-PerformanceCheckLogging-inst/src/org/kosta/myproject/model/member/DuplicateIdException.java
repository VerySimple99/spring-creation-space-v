package org.kosta.myproject.model.member;

public class DuplicateIdException extends Exception {
	private static final long serialVersionUID = -855747128698634588L;
	public DuplicateIdException(String message) {
		super(message);
	}
}
