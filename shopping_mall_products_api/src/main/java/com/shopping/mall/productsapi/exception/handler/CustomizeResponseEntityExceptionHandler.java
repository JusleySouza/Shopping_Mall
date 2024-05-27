package com.shopping.mall.productsapi.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shopping.mall.productsapi.config.LoggerConfig;
import com.shopping.mall.productsapi.exception.DuplicateCategoryException;
import com.shopping.mall.productsapi.exception.DuplicateSkuException;
import com.shopping.mall.productsapi.exception.ExceptionResponse;
import com.shopping.mall.productsapi.exception.ResourceNotFoundException;
import com.shopping.mall.productsapi.exception.UpdateNotAllowedException;
import com.shopping.mall.productsapi.exception.ValidationException;

import lombok.Generated;

@Generated
@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_EXCEPTION.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_EXCEPTION.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ExceptionResponse> handleFieldValidationExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_EXCEPTION.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(UpdateNotAllowedException.class)
	public final ResponseEntity<ExceptionResponse> handleUpdateNotAllowedExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_EXCEPTION.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(DuplicateCategoryException.class)
	public final ResponseEntity<ExceptionResponse> handleDuplicateCategoriesExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_EXCEPTION.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(DuplicateSkuException.class)
	public final ResponseEntity<ExceptionResponse> handleDuplicateSkuExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_EXCEPTION.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	
}
