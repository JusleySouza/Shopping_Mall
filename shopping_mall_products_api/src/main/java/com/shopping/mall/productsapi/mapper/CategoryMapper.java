package com.shopping.mall.productsapi.mapper;

import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.dto.RequestCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseCategoryDTO;

public class CategoryMapper {

	public static Category toModel(RequestCategoryDTO requestCategoryDTO) {
		return Category.builder()
				.nameCategory(requestCategoryDTO.getNameCategory())
				.build();
	}
	
	public static ResponseCategoryDTO modelToResponseCategoryDTO(Category category) {
		return ResponseCategoryDTO.builder()
				.id(category.getId())
				.nameCategory(category.getNameCategory())
				.build();
	}
	
	public static Category updateCategory(Category category, RequestCategoryDTO requestCategoryDTO) {
		category.setNameCategory(requestCategoryDTO.getNameCategory());
		return category;
	}
	
}
