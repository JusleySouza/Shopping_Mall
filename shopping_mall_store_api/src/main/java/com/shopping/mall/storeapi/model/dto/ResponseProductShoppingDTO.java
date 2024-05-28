package com.shopping.mall.storeapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ResponseProductShoppingDTO {
	
	@JsonProperty("nameProduct")
	private String nameProduct;
	@JsonProperty("valueUnitary")
	private double valueUnitary;

}
