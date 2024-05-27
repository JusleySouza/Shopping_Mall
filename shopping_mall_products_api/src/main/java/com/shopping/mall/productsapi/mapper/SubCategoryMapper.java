package com.shopping.mall.productsapi.mapper;

import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.SubCategory;
import com.shopping.mall.productsapi.model.dto.RequestSubCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSubCategoryDTO;

public class SubCategoryMapper {
	
	public static SubCategory toModel(RequestSubCategoryDTO requestSubCategoryDTO, Category category) {
		return SubCategory.builder()
				.nameSubCategory(requestSubCategoryDTO.getNameSubCategory())
				.description(requestSubCategoryDTO.getDescription())
				.category(category)
				.build();
	}
	
	public static ResponseSubCategoryDTO modelToResponseSubCategoryDTO(SubCategory subCategory) {
		return ResponseSubCategoryDTO.builder()
				.id(subCategory.getId())
				.nameSubCategory(subCategory.getNameSubCategory())
				.idCategory(subCategory.getCategory().getId())
				.build();
	}
	
	public static SubCategory updateSubCategory(SubCategory subCategory, RequestSubCategoryDTO requestSubCategoryDTO, Category category) {
		subCategory.setNameSubCategory(requestSubCategoryDTO.getNameSubCategory());
		subCategory.setDescription(requestSubCategoryDTO.getDescription());
		subCategory.setCategory(category);
		return subCategory;
	}

}
