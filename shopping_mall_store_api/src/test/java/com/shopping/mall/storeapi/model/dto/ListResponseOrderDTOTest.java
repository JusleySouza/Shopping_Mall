package com.shopping.mall.storeapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class ListResponseOrderDTOTest {

	private ListResponseOrderDTO expectedListResponseOrderDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		expectedListResponseOrderDTO = ClassBuilder.listResponseOrderDTOBuilder();
	}

	@Test
	void builder() {
		ListResponseOrderDTO listResponseOrderDTO = ListResponseOrderDTO.builder()
				.order(List.of(ClassBuilder.responseOrderDTOBuilder()))
				.build();
		assertEquals(expectedListResponseOrderDTO.toString(), listResponseOrderDTO.toString());
	}

	@Test
	void setter() {
		ListResponseOrderDTO listResponseOrderDTO = new ListResponseOrderDTO();
		listResponseOrderDTO.setOrder(List.of(ClassBuilder.responseOrderDTOBuilder()));
		assertEquals(expectedListResponseOrderDTO.toString(), listResponseOrderDTO.toString());
	}
	
}
