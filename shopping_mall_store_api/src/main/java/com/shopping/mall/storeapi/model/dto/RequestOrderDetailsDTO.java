package com.shopping.mall.storeapi.model.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
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
public class RequestOrderDetailsDTO {
	
	@ApiModelProperty(notes = "Id product", example = "000acde0-4d72-4049-9705-0eb5ce87e2fa", required = true)
	private UUID idProduct;
	@ApiModelProperty(notes = "Amount product", example = "5", required = true)
	private int amount;

}
