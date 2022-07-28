package hoon.pepper.common.advice;

import hoon.pepper.common.exception.PlatformException;
import hoon.pepper.common.exception.UnauthorizedException;
import hoon.pepper.common.wrapper.ResultResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PlatformException.class)
	public ResponseEntity<ResultResponse> handleException(PlatformException e) {
		ResultResponse resultResponse = new ResultResponse(e.getHttpStatus());
		resultResponse.setMessage(e.getMessage());
		resultResponse.setCode(e.getCode());

		log.debug("#### {}",resultResponse);
		return new ResponseEntity<>(resultResponse, e.getHttpStatus());
	}

	@ExceptionHandler({ValidationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResultResponse handleValidateException(Exception e) {
		ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST);
		resultResponse.setMessage(e.getMessage());
		resultResponse.setCode(40099);
		log.warn("#### {}", (Object) e.getStackTrace());
		return resultResponse;
	}

	@ExceptionHandler({UnauthorizedException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResultResponse handleException(UnauthorizedException e) {
		ResultResponse resultResponse = new ResultResponse(HttpStatus.UNAUTHORIZED);
		resultResponse.setCode(40100);
		resultResponse.setMessage("Unauthorized");
		return resultResponse;
	}

	@ExceptionHandler({AccessDeniedException.class})
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResultResponse handleException(AccessDeniedException e) {
		ResultResponse resultResponse = new ResultResponse(HttpStatus.FORBIDDEN);
		resultResponse.setCode(40300);
		resultResponse.setMessage(e.getMessage());
		return resultResponse;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResultResponse handleException(Exception e) {
		log.error("#### {} ", e.getMessage());
		ResultResponse resultResponse = new ResultResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		resultResponse.setMessage(e.getMessage());

		for (StackTraceElement stack : e.getStackTrace()) {
			log.warn("#### {}", stack);
		}

		return resultResponse;
	}
}
