package com.shopping.mall.discountsapi.app.dto;

import java.util.UUID;

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
public class ResponseCouponDTO {
	
	private UUID id;
	private String code;
	private Double percentage;
	private int minQuantityProducts;
	private Double minOrderValue;
	private Double maxDiscount;
	private String created;
	private String expiration;
	private Boolean active;

}
