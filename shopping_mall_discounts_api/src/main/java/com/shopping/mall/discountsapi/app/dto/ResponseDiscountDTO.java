package com.shopping.mall.discountsapi.app.dto;

import java.util.UUID;

import com.shopping.mall.discountsapi.app.enums.TypeDiscount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDiscountDTO {
	
	private UUID id;
	private TypeDiscount typeDiscount;
	private UUID idObjectDiscount;
	private Double percentageDiscount;
	private String created;
	private String expiration;
	private Boolean active;

}
