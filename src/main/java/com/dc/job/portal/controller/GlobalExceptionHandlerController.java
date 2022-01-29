package com.dc.job.portal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dc.job.portal.constant.Constants;
import com.dc.job.portal.dto.ErrorDto;
import com.dc.job.portal.exception.BusinessException;
import com.dc.job.portal.exception.InvalidCredentialsException;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ErrorDto errorDto = new ErrorDto();
		errorDto.setErrorCode(Constants.ERROR_CODE_101);
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		errorDto.setErrors(errors);
		errorDto.setErrorMessage("Validation failure");
		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ErrorDto> handleSQLException(HttpServletRequest request, Exception ex) {
		logger.info("SQLException Occured:: URL=" + request.getRequestURL());
		return new ResponseEntity<ErrorDto>(getErrorDto(ex,Constants.ERROR_CODE_103), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDto> handleBusinessException(HttpServletRequest request, Exception ex) {
		logger.info("BusinessException Occured:: URL=" + request.getRequestURL());
		return new ResponseEntity<ErrorDto>(getErrorDto(ex,Constants.ERROR_CODE_102), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorDto getErrorDto(Exception ex,String errorCode) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setErrorCode(errorCode);
		errorDto.setErrorMessage(ex.getMessage());
		return errorDto;
	}
	
	

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorDto> handleInvalidCredentialsException(HttpServletRequest request, Exception ex) {
		logger.info("InvalidCredentialsException Occured:: URL=" + request.getRequestURL());
		ErrorDto errorDto = new ErrorDto();
		errorDto.setErrorCode("101");
		errorDto.setErrorMessage(Constants.INVALID_CREDENTIALS);
		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.UNAUTHORIZED);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "IOException occured")
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorDto> handleIOException(HttpServletRequest request, Exception ex) {
		logger.error("IOException handler executed>>>" + ex.getMessage());
		return new ResponseEntity<ErrorDto>(getErrorDto(ex,Constants.ERROR_CODE_105), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> handleException(HttpServletRequest request, Exception ex) {
		logger.error("Exception handler executed>>>>" + ex.getMessage());
		// returning 404 error code
		return new ResponseEntity<ErrorDto>(getErrorDto(ex,Constants.ERROR_CODE_104), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}