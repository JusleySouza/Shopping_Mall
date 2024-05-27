package com.shopping.mall.productsapi.mapper;

import java.time.LocalDate;

import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.Product;
import com.shopping.mall.productsapi.model.SubCategory;
import com.shopping.mall.productsapi.model.dto.RequestProductDTO;
import com.shopping.mall.productsapi.model.dto.ResponseProductDTO;

public class ProductMapper {

	public static Product toModel(RequestProductDTO requestProductDTO, SubCategory subCategory, Category category) {
		return Product.builder()
				 .name(requestProductDTO.getName())
				 .description(requestProductDTO.getDescription())
				 .sku(requestProductDTO.getSku())
				 .valueUnitary(requestProductDTO.getValueUnitary())
				 .stock(requestProductDTO.getStock())
				 .created(LocalDate.now())
				 .subCategory(subCategory)
				 .category(category)
				 .active(Boolean.TRUE)
				.build();
	}
	
	public static ResponseProductDTO modelToResponseProductDTO(Product product) {
		return ResponseProductDTO.builder()
				 .id(product.getId())
				 .name(product.getName())
				 .description(product.getDescription())
				 .sku(product.getSku())
				 .valueUnitary(product.getValueUnitary())
				 .stock(product.getStock())
				 .created(product.getCreated().toString().split(" ")[0])
				 .idSubCategory(product.getSubCategory() != null ? product.getSubCategory().getId() : null)
				 .idCategory(product.getCategory().getId())
				.build();
	}
	
	public static Product updateProduct(Product product, RequestProductDTO requestProductDTO, SubCategory subCategory, Category category) {
		product.setName(requestProductDTO.getName());
		product.setDescription(requestProductDTO.getDescription());
		product.setSku(requestProductDTO.getSku());
		product.setValueUnitary(requestProductDTO.getValueUnitary());
		product.setStock(requestProductDTO.getStock());
		product.setChanged(LocalDate.now());
		product.setSubCategory(subCategory);
		product.setCategory(category);
		return product;
	}
	
	public static Product productDelete(Product product) {
		product.setActive(Boolean.FALSE);
		product.setChanged(LocalDate.now());
		return product;
	}
	
}
