package com.shopping.mall.userapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shopping.mall.userapi.services.implement.AddressServicesImplement;
import com.shopping.mall.userapi.services.implement.UsersServicesImplement;

import lombok.Generated;

@Generated
public class LoggerConfig {
	
	public static final Logger LOGGER_USER = LoggerFactory.getLogger(UsersServicesImplement.class);
	
	public static final Logger LOGGER_ADDRESS = LoggerFactory.getLogger(AddressServicesImplement.class);

}
