package com.shopping.mall.productsapi.model.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Generated
public class ResponseSuccess {
	private String message;
	private Date timestamp;

}
