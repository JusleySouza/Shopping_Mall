package com.shopping.mall.productsapi.model.dto;

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
public class ResponseProductDTO {
	
	private UUID id;
	private String name;
	private String description;
	private int sku;
	private double valueUnitary;
	private int stock;
	private String created;
	private UUID idSubCategory;
	private UUID idCategory;
	
}
