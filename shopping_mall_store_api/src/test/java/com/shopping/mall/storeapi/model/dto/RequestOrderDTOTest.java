package com.shopping.mall.storeapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class RequestOrderDTOTest {
	
	private RequestOrderDTO expectedRequestOrderDTO;
	private UUID idUser;

	@BeforeEach
	void setUp() throws Exception {
		idUser = UUID.randomUUID();
		expectedRequestOrderDTO = ClassBuilder.requestOrderDTOBuilder();
		expectedRequestOrderDTO.setIdUser(idUser);
	}

	@Test
	void builder() {
		RequestOrderDTO requestOrderDTO = RequestOrderDTO.builder()
				.idUser(idUser)
				.orderDetails(List.of(ClassBuilder.requestOrderDetailsDTOBuilder()))
				.build();
		assertEquals(expectedRequestOrderDTO.toString(), requestOrderDTO.toString());
	}
	
	@Test
	void setter() {
		RequestOrderDTO requestOrderDTO = new RequestOrderDTO();
		requestOrderDTO.setIdUser(idUser);
		requestOrderDTO.setOrderDetails(List.of(ClassBuilder.requestOrderDetailsDTOBuilder()));
		assertEquals(expectedRequestOrderDTO.toString(), requestOrderDTO.toString());
	}

}
