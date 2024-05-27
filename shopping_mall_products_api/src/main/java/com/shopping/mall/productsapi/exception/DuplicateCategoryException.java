package com.shopping.mall.productsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DuplicateCategoryException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DuplicateCategoryException(String exception) {
		super(exception);
	}
}
