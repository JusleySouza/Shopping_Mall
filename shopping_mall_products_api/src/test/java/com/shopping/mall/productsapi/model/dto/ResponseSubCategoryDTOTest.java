package com.shopping.mall.productsapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class ResponseSubCategoryDTOTest {

	private UUID id;
	private ResponseSubCategoryDTO expectedResponseSubCategoryDTO;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseSubCategoryDTO = ClassBuilder.responseSubCategoryDTOBuilder();
		expectedResponseSubCategoryDTO.setIdCategory(id);
		expectedResponseSubCategoryDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseSubCategoryDTO responseSubCategoryDTO = ResponseSubCategoryDTO.builder()
				.nameSubCategory("televisores")
				.idCategory(id)
				.id(id)
				.build();
		assertEquals(expectedResponseSubCategoryDTO.toString(), responseSubCategoryDTO.toString());
	}
	
	@Test
	void setter() {
		ResponseSubCategoryDTO responseSubCategoryDTO = new ResponseSubCategoryDTO();
		responseSubCategoryDTO.setId(id);
		responseSubCategoryDTO.setNameSubCategory("televisores");
		responseSubCategoryDTO.setIdCategory(id);
		assertEquals(expectedResponseSubCategoryDTO.toString(), responseSubCategoryDTO.toString());
	}

}
