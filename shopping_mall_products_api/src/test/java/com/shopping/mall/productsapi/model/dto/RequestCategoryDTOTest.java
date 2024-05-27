package com.shopping.mall.productsapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class RequestCategoryDTOTest {
	
	private RequestCategoryDTO expectedRequestCategoryDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedRequestCategoryDTO = ClassBuilder.requestCategoryDTOBuilder();
	}

	@Test
	void builder() {
		RequestCategoryDTO requestCategoryDTO = RequestCategoryDTO.builder()
				.nameCategory("eletronicos")
				.build();
		assertEquals(expectedRequestCategoryDTO.toString(), requestCategoryDTO.toString());
	}
	
	@Test
	void setter() {
		RequestCategoryDTO requestCategoryDTO = new RequestCategoryDTO();
		requestCategoryDTO.setNameCategory("eletronicos");
		assertEquals(expectedRequestCategoryDTO.toString(), requestCategoryDTO.toString());
	}

}
