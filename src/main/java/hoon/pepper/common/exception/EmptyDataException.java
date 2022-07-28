package hoon.pepper.common.exception;

public class EmptyDataException extends BadRequestException {
	public EmptyDataException() {
		super("Empty Data", 40002);
	}

	public EmptyDataException(String message) {
		super(message, 40002);
	}

	public EmptyDataException(String message, int code) {
		super(message, code);
	}
}
