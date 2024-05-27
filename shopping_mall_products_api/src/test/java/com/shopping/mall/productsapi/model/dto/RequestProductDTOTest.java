package com.shopping.mall.productsapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class RequestProductDTOTest {
	private UUID id;
	private RequestProductDTO expectedRequestProductDTO;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedRequestProductDTO = ClassBuilder.requestProductDTOBuilder();
		expectedRequestProductDTO.setIdSubCategory(id);
		expectedRequestProductDTO.setIdCategory(id);
	}

	@Test
	void builder() {
		RequestProductDTO requestProductDTO = RequestProductDTO.builder()
				.name("televisão")
				.description("produtos novos, portateis e de facil manuseio")
				.sku(123)
				.valueUnitary(1324.99)
				.stock(5)
				.idSubCategory(id)
				.idCategory(id)
				.build();
		assertEquals(expectedRequestProductDTO.toString(), requestProductDTO.toString());
	}
	
	@Test
	void setter() {
		RequestProductDTO requestProductDTO = new RequestProductDTO();
		requestProductDTO.setName("televisão");
		requestProductDTO.setDescription("produtos novos, portateis e de facil manuseio");
		requestProductDTO.setSku(123);
		requestProductDTO.setValueUnitary(1324.99);
		requestProductDTO.setStock(5);
		requestProductDTO.setIdSubCategory(id);
		requestProductDTO.setIdCategory(id);
		assertEquals(expectedRequestProductDTO.toString(), requestProductDTO.toString());
	}

}
