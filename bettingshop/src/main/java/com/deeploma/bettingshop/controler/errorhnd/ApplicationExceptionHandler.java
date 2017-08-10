package com.deeploma.bettingshop.controler.errorhnd;

import static com.deeploma.bettingshop.exception.ErrorType.UNKNOWN_INTERNAL_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deeploma.bettingshop.exception.ApplicationException;
import com.deeploma.bettingshop.exception.ErrorType;

/**
 * Mapper for application exception.
 * 
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@ExceptionHandler(ApplicationException.class)
	@ResponseBody	
	public ResponseEntity<ErrorInfo> toResponse(ApplicationException applicationException) {
		logger.error("[Application exception (Error Type = " + applicationException.getErrorType() + ")]", applicationException);
		
		if (applicationException.getErrorType() != null) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorCode(applicationException.getErrorType().name());
			errorInfo.setLocalized(applicationException.getErrorType().toString());
			
			return new ResponseEntity<ErrorInfo>(errorInfo, BAD_REQUEST);
		}
		
		logger.error("[Application exception] ovde nije trebao da dodje nikada ");
		return internalError();
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseBody	
	public ResponseEntity<ErrorInfo> toResponse(Throwable applicationException) {
		logger.error("[Application exception - unknown ", applicationException);
		return internalError();
	}

	private ResponseEntity<ErrorInfo> internalError() {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(UNKNOWN_INTERNAL_ERROR.name());
		errorInfo.setLocalized(UNKNOWN_INTERNAL_ERROR.getDescription());
		return new ResponseEntity<ErrorInfo>(errorInfo, INTERNAL_SERVER_ERROR);
	}
}
