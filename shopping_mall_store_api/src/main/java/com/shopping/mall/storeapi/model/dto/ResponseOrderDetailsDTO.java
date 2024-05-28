package com.shopping.mall.storeapi.model.dto;

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
public class ResponseOrderDetailsDTO {
	
	private UUID id;
	private ResponseProductShoppingDTO product;
	private int amount;
	private double subtotal;

}
