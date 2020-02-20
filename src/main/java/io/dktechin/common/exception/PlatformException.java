package io.dktechin.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * MakersException
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
@Getter
@Setter
public class PlatformException extends RuntimeException {
	private HttpStatus httpStatus;
	private int code;
	private Object errorData;

	public PlatformException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public PlatformException(String message) {
		super(message);
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.code = 50000;
	}

	public PlatformException(HttpStatus httpStatus, int code, String message) {
		super(message);
		this.httpStatus = httpStatus;
		this.code = code;
	}
	public PlatformException(HttpStatus httpStatus, int code, String message, Object errorData) {
		super(message);
		this.httpStatus = httpStatus;
		this.code = code;
		this.errorData = errorData;
	}

	public PlatformException(String message, Object errorData) {
		super(message);
		this.errorData = errorData;
	}


}

