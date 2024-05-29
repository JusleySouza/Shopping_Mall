package com.shopping.mall.storeapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class ResponseProductShoppingDTOTest {

	private ResponseProductShoppingDTO expectedResponseProductShoppingDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		expectedResponseProductShoppingDTO = ClassBuilder.responseProductShoppingDTOBuilder();
	}

	@Test
	void builder() {
		ResponseProductShoppingDTO responseProductShoppingDTO = ResponseProductShoppingDTO.builder()
				.nameProduct("MacBook Pro")
				.valueUnitary(441.66)
				.build();
		assertEquals(expectedResponseProductShoppingDTO.toString(), responseProductShoppingDTO.toString());
	}

	@Test
	void setter() {
		ResponseProductShoppingDTO responseProductShoppingDTO = new ResponseProductShoppingDTO();
		responseProductShoppingDTO.setNameProduct("MacBook Pro");
		responseProductShoppingDTO.setValueUnitary(441.66);
		assertEquals(expectedResponseProductShoppingDTO.toString(), responseProductShoppingDTO.toString());
	}
	
}
