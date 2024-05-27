package com.shopping.mall.productsapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class ResponseProductDTOTest {

	private UUID id;
	private ResponseProductDTO expectedResponseProductDTO;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseProductDTO = ClassBuilder.responseProductDTOBuilder();
		expectedResponseProductDTO.setId(id);
		expectedResponseProductDTO.setIdSubCategory(id);
		expectedResponseProductDTO.setIdCategory(id);
	}

	@Test
	void builder() {
		ResponseProductDTO responseProductDTO = ResponseProductDTO.builder()
				.id(id)
				.name("televisão")
				.description("produtos novos, portateis e de facil manuseio")
				.sku(123)
				.valueUnitary(1324.99)
				.stock(5)
				.created(LocalDate.now().toString())
				.idSubCategory(id)
				.idCategory(id)
				.build();
		assertEquals(expectedResponseProductDTO.toString(), responseProductDTO.toString());
	}
	
	@Test
	void setter() {
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
		assertEquals(expectedResponseProductDTO.toString(), responseProductDTO.toString());
	}
}
