package com.shopping.mall.storeapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class ResponseOrderDetailsDTOTest {

	private UUID id;
	private ResponseOrderDetailsDTO expectedResponseOrderDetailsDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseOrderDetailsDTO = ClassBuilder.responseOrderDetailsDTOBuilder();
		expectedResponseOrderDetailsDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseOrderDetailsDTO responseOrderDetailsDTO = ResponseOrderDetailsDTO.builder()
				.id(id)
				.product(ClassBuilder.responseProductShoppingDTOBuilder())
				.amount(3)
				.subtotal(1324.98)
				.build();
		assertEquals(expectedResponseOrderDetailsDTO.toString(), responseOrderDetailsDTO.toString());
	}

	@Test
	void setter() {
		ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO();
		responseOrderDetailsDTO.setId(id);
		responseOrderDetailsDTO.setProduct(ClassBuilder.responseProductShoppingDTOBuilder());
		responseOrderDetailsDTO.setAmount(3);
		responseOrderDetailsDTO.setSubtotal(1324.98);
		assertEquals(expectedResponseOrderDetailsDTO.toString(), responseOrderDetailsDTO.toString());
	}
	
}
