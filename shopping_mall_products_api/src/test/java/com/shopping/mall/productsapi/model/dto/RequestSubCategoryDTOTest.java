package com.shopping.mall.productsapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class RequestSubCategoryDTOTest {

	private UUID id;
	private RequestSubCategoryDTO expectedRequestSubCategoryDTO;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedRequestSubCategoryDTO = ClassBuilder.requestSubCategoryDTOBuilder();
		expectedRequestSubCategoryDTO.setIdCategory(id);
	}

	@Test
	void builder() {
		RequestSubCategoryDTO requestSubCategoryDTO = RequestSubCategoryDTO.builder()
				.nameSubCategory("televisores")
				.description("novos, semi-novos, com nota fiscal")
				.idCategory(id)
				.build();
		assertEquals(expectedRequestSubCategoryDTO.toString(), requestSubCategoryDTO.toString());
	}
	
	@Test
	void setter() {
		RequestSubCategoryDTO requestSubCategoryDTO = new RequestSubCategoryDTO();
		requestSubCategoryDTO.setNameSubCategory("televisores");
		requestSubCategoryDTO.setDescription("novos, semi-novos, com nota fiscal");
		requestSubCategoryDTO.setIdCategory(id);
		assertEquals(expectedRequestSubCategoryDTO.toString(), requestSubCategoryDTO.toString());
	}

}
