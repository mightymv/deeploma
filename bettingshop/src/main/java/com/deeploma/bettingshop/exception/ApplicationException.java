package com.deeploma.bettingshop.exception;

public class ApplicationException extends RuntimeException {
	
private static final long serialVersionUID = 1427914712628450861L;
	
	private final ErrorType errorType;

	/**
	 * Constructs new exception.
	 */
	public ApplicationException() {
		super();
		
		this.errorType = null;
	}
	
	/**
	 * Constructs new exception.
	 * 
	 * @param cause - the cause of exception
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
		
		this.errorType = null;
	}
	
	/**
	 * Constructs new exception.
	 * 
	 * @param errorType - error type
	 */
	public ApplicationException(ErrorType errorType) {
		super(errorType.toString());
		
		this.errorType = errorType;
	}

	
	/**
	 * Constructs new exception.
	 * 
	 * @param cause - the cause of exception
	 * @param errorType - error type
	 */
	public ApplicationException(ErrorType errorType, Throwable cause) {
		super(errorType.toString(), cause);
		
		this.errorType = errorType;
	}
	
	/**
	 * @return error type; {@code null} if error type is not specified
	 */
	public ErrorType getErrorType() {
		return errorType;
	}

}
