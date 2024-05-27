package com.shopping.mall.productsapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class ResponseCategoryDTOTest {

	private UUID id;
	private ResponseCategoryDTO expectedResponseCategoryDTO;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseCategoryDTO = ClassBuilder.responseCategoryDTOBuilder();
		expectedResponseCategoryDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseCategoryDTO responseCategoryDTO = ResponseCategoryDTO.builder()
				.id(id)
				.nameCategory("eletronicos")
				.build();
		assertEquals(expectedResponseCategoryDTO.toString(), responseCategoryDTO.toString());
	}
	
	@Test
	void setter() {
		ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
		responseCategoryDTO.setId(id);
		responseCategoryDTO.setNameCategory("eletronicos");
		assertEquals(expectedResponseCategoryDTO.toString(), responseCategoryDTO.toString());
	}

}
