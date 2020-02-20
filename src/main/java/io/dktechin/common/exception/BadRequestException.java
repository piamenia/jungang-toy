package io.dktechin.common.exception;

import org.springframework.http.HttpStatus;

/**
 * BadRequestException
 * <pre>
 * Describe here
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 * april, 1.0, 27/12/2018 초기작성
 * </pre>
 *
 * @author april
 * @version 1.0
 *
 */
public class BadRequestException extends PlatformException {

	public BadRequestException() {
		super(HttpStatus.BAD_REQUEST, BadRequestDetail.INVALID_PARAMETER.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, BadRequestDetail.INVALID_PARAMETER.value(), message);
	}

	public BadRequestException(String message, int code) {
		super(HttpStatus.BAD_REQUEST, code, message);
	}

	public BadRequestException(String message, BadRequestDetail detail) {
		super(HttpStatus.BAD_REQUEST, detail.value(), message);
	}

	public BadRequestException(String message, int code, Object error) {
		super(HttpStatus.BAD_REQUEST, code, message, error);
	}
}
