package io.dktechin.common.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResultResponse<T> {
	private int status;
	private int code;
	private String message;
	private T result;

	public ResultResponse(T result) {
		this.status = HttpStatus.OK.value();
		this.message = "success";
		this.result = result;
	}

	public ResultResponse(HttpStatus httpStatus) {
		this.status = httpStatus.value();
	}
}

