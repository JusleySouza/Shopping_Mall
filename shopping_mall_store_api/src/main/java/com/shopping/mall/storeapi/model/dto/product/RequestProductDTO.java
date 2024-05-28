package com.shopping.mall.storeapi.model.dto.product;

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
public class RequestProductDTO {
	
	private String name;
	private String description;
	private int sku;
	private Double valueUnitary;
	private int stock;
	private UUID idSubCategory;
	private UUID idCategory;

}
