package com.shopping.mall.productsapi.mapper;

import java.util.Date;

import com.shopping.mall.productsapi.model.dto.ResponseSuccess;

import lombok.Generated;

@Generated
public class ResponseSuccessMapper {

	public static ResponseSuccess buildResponseSuccess(String message) {
		return ResponseSuccess.builder()
				.message(message)
				.timestamp(new Date())
				.build();
	}
}
