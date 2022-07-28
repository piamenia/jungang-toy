package hoon.pepper.common.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends PlatformException {
	public ForbiddenException() {
		super(HttpStatus.FORBIDDEN, 40300, "Forbidden.");
	}

	public ForbiddenException(String message) {
		super(HttpStatus.FORBIDDEN, 40300, message);
	}
}
