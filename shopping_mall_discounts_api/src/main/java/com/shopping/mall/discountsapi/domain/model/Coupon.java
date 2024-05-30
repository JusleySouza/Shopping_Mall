package com.shopping.mall.discountsapi.domain.model;

import java.time.LocalDate;
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
public class Coupon {
	
	private UUID id;
	private String code;
	private Double percentage;
	private int minQuantityProducts;
	private Double minOrderValue;
	private Double maxDiscount;
	private LocalDate created;
	private LocalDate expiration;
	private Boolean active;

}
