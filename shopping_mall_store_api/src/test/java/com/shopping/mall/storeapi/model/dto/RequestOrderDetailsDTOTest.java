package com.shopping.mall.storeapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class RequestOrderDetailsDTOTest {
	
	private RequestOrderDetailsDTO expectedRequestOrderDetailsDTO;
	private UUID idProduct;

	@BeforeEach
	void setUp() throws Exception {
		idProduct = UUID.randomUUID();
		expectedRequestOrderDetailsDTO = ClassBuilder.requestOrderDetailsDTOBuilder();
		expectedRequestOrderDetailsDTO.setIdProduct(idProduct);
	}

	@Test
	void builder() {
		RequestOrderDetailsDTO requestOrderDetailsDTO = RequestOrderDetailsDTO.builder()
				.idProduct(idProduct)
				.amount(3)
				.build();
		assertEquals(expectedRequestOrderDetailsDTO.toString(), requestOrderDetailsDTO.toString());
	}
	
	@Test
	void setter() {
		RequestOrderDetailsDTO requestOrderDetailsDTO = new RequestOrderDetailsDTO();
		requestOrderDetailsDTO.setIdProduct(idProduct);
		requestOrderDetailsDTO.setAmount(3);
		assertEquals(expectedRequestOrderDetailsDTO.toString(), requestOrderDetailsDTO.toString());
	}

}
