package com.shopping.mall.userapi.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shopping.mall.userapi.config.LoggerConfig;
import com.shopping.mall.userapi.exception.DuplicateDocumentsException;
import com.shopping.mall.userapi.exception.ExceptionResponse;
import com.shopping.mall.userapi.exception.ResourceNotFoundException;
import com.shopping.mall.userapi.exception.UpdateNotAllowedException;
import com.shopping.mall.userapi.exception.ValidationException;

import lombok.Generated;

@Generated
@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_USER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_USER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateDocumentsException.class)
	public final ResponseEntity<ExceptionResponse> handleDuplicateDocumentsExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_USER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(UpdateNotAllowedException.class)
	public final ResponseEntity<ExceptionResponse> handleUpdateNotAllowedExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_USER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ExceptionResponse> handleFieldValidationExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_USER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}
