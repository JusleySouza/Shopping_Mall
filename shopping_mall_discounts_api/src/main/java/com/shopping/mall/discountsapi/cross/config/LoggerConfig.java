package com.shopping.mall.discountsapi.cross.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shopping.mall.discountsapi.app.service.impl.CouponServiceImplement;
import com.shopping.mall.discountsapi.app.service.impl.DiscountServiceImplement;

import lombok.Generated;

@Generated
public class LoggerConfig {
	
	public static final Logger LOGGER_COUPON = LoggerFactory.getLogger(CouponServiceImplement.class);

	public static final Logger LOGGER_DISCOUNT = LoggerFactory.getLogger(DiscountServiceImplement.class);

	
}
