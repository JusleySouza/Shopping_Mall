package com.shopping.mall.productsapi.test.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.Product;
import com.shopping.mall.productsapi.model.SubCategory;
import com.shopping.mall.productsapi.model.dto.RequestCategoryDTO;
import com.shopping.mall.productsapi.model.dto.RequestProductDTO;
import com.shopping.mall.productsapi.model.dto.RequestSubCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseProductDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSubCategoryDTO;

public class ClassBuilder {
	
	private static UUID id = UUID.randomUUID();
	
	public static Category categoryBuilder() {
		Category category = new Category();
		category.setId(id);
		category.setNameCategory("eletronicos");
		category.setListProduct(List.of(productBuilder()));	
		category.setListSubCategory(List.of(subCategoryBuilder()));	
		return category;
	}
	
	public static Product productBuilder() {
		Product product = new Product();
		product.setId(id);
		product.setName("televisão");
		product.setDescription("produtos novos, portateis e de facil manuseio");
		product.setSku(123);
		product.setValueUnitary(1324.99);
		product.setStock(5);
		product.setActive(Boolean.TRUE);
		product.setCreated(LocalDate.now());
		product.setChanged(LocalDate.now());
		return product;
	}
	
	public static SubCategory subCategoryBuilder() {
		SubCategory subCategory = new SubCategory();
		subCategory.setId(id);
		subCategory.setNameSubCategory("televisores");
		subCategory.setDescription("novos, semi-novos, com nota fiscal");
		subCategory.setListProducts(List.of(productBuilder()));	
		return subCategory;
	}
	
	public static RequestCategoryDTO requestCategoryDTOBuilder() {
		RequestCategoryDTO requestCategoryDTO = new RequestCategoryDTO();
		requestCategoryDTO.setNameCategory("eletronicos");
		return requestCategoryDTO;
	}
	
	public static RequestProductDTO requestProductDTOBuilder() {
		RequestProductDTO requestProductDTO = new RequestProductDTO();
		requestProductDTO.setName("televisão");
		requestProductDTO.setDescription("produtos novos, portateis e de facil manuseio");
		requestProductDTO.setSku(123);
		requestProductDTO.setValueUnitary(1324.99);
		requestProductDTO.setStock(5);
		requestProductDTO.setIdSubCategory(id);
		requestProductDTO.setIdCategory(id);
		return requestProductDTO;
	}
	
	public static RequestSubCategoryDTO requestSubCategoryDTOBuilder() {
		RequestSubCategoryDTO requestSubCategoryDTO = new RequestSubCategoryDTO();
		requestSubCategoryDTO.setNameSubCategory("televisores");
		requestSubCategoryDTO.setDescription("novos, semi-novos, com nota fiscal");
		requestSubCategoryDTO.setIdCategory(id);
		return requestSubCategoryDTO;
	}
	
	public static ResponseCategoryDTO responseCategoryDTOBuilder() {
		ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
		responseCategoryDTO.setId(id);
		responseCategoryDTO.setNameCategory("eletronicos");
		return responseCategoryDTO;
	}
	
	public static ResponseProductDTO responseProductDTOBuilder() {
		ResponseProductDTO responseProductDTO = new ResponseProductDTO();
		responseProductDTO.setId(id);
		responseProductDTO.setName("televisão");
		responseProductDTO.setDescription("produtos novos, portateis e de facil manuseio");
		responseProductDTO.setSku(123);
		responseProductDTO.setValueUnitary(1324.99);
		responseProductDTO.setStock(5);
		responseProductDTO.setCreated(LocalDate.now().toString());
		responseProductDTO.setIdSubCategory(id);
		responseProductDTO.setIdCategory(id);
		return responseProductDTO;
	}
	
	public static ResponseSubCategoryDTO responseSubCategoryDTOBuilder() {
		ResponseSubCategoryDTO responseSubCategoryDTO = new ResponseSubCategoryDTO();
		responseSubCategoryDTO.setId(id);
		responseSubCategoryDTO.setNameSubCategory("televisores");
		responseSubCategoryDTO.setIdCategory(id);
		return responseSubCategoryDTO;
	}

}
