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
public class ResponseSubCategoryDTO {
	
	private UUID id;
	private String nameSubCategory;
	private UUID idCategory;

}
