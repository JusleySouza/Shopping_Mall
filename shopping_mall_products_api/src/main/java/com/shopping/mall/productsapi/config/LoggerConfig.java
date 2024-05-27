package com.shopping.mall.productsapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shopping.mall.productsapi.exception.handler.CustomizeResponseEntityExceptionHandler;
import com.shopping.mall.productsapi.services.implement.CategoryServicesImplement;
import com.shopping.mall.productsapi.services.implement.ProductServicesImplement;
import com.shopping.mall.productsapi.services.implement.SubCategoryServicesImplement;

import lombok.Generated;

@Generated
public class LoggerConfig {
	
	public static final Logger LOGGER_CATEGORY = LoggerFactory.getLogger(CategoryServicesImplement.class);
	public static final Logger LOGGER_SUB_CATEGORY = LoggerFactory.getLogger(SubCategoryServicesImplement.class);
	public static final Logger LOGGER_PRODUCT = LoggerFactory.getLogger(ProductServicesImplement.class);
	public static final Logger LOGGER_EXCEPTION = LoggerFactory.getLogger(CustomizeResponseEntityExceptionHandler.class);

}
