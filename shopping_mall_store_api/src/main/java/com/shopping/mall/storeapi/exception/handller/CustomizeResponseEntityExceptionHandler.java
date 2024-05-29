package com.shopping.mall.storeapi.exception.handller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shopping.mall.storeapi.config.LoggerConfig;
import com.shopping.mall.storeapi.exception.ExceptionResponse;
import com.shopping.mall.storeapi.exception.ResourceNotFoundException;
import com.shopping.mall.storeapi.exception.ValidationException;

import feign.FeignException;
import lombok.Generated;

@Generated
@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_ORDER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_ORDER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ExceptionResponse> handleFieldValidationExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		LoggerConfig.LOGGER_ORDER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(FeignException.class)
	public final ResponseEntity<ExceptionResponse> handleFeignExceptions(FeignException exception, WebRequest request){
		
		Pattern part = Pattern.compile("(Insufficient)(.*)([\\d]\\.)");
		Matcher mat = part.matcher(exception.getMessage());
		
		String a = "";
		while(mat.find()) {
		
			a = mat.group();
			System.out.println(a);
		}
		System.out.println("aaa");
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), a, request.getDescription(false));
		LoggerConfig.LOGGER_ORDER.error(exception.getMessage());
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}
