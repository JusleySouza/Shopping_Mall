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
public class Discount {
	
	private UUID id;
	private String typeDiscount;
	private UUID idObjectDiscount;
	private Double percentageDiscount;
	private Boolean active;
	private LocalDate created;
	private LocalDate expiration;

}
