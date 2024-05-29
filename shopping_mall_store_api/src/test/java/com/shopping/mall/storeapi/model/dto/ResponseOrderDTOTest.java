package com.shopping.mall.storeapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class ResponseOrderDTOTest {

	private UUID id;
	private ResponseOrderDTO expectedResponseOrderDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseOrderDTO = ClassBuilder.responseOrderDTOBuilder();
		expectedResponseOrderDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseOrderDTO responseOrderDTO = ResponseOrderDTO.builder()
				.id(id)
				.status(1)
				.total(1324.98)
				.created(LocalDate.now())
				.changed(LocalDate.now())
				.listOrderDetails(List.of(ClassBuilder.responseOrderDetailsDTOBuilder()))
				.build();
		assertEquals(expectedResponseOrderDTO.toString(), responseOrderDTO.toString());
	}

	@Test
	void setter() {
		ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
		responseOrderDTO.setId(id);
		responseOrderDTO.setStatus(1);
		responseOrderDTO.setTotal(1324.98);
		responseOrderDTO.setCreated(LocalDate.now());
		responseOrderDTO.setChanged(LocalDate.now());
		responseOrderDTO.setListOrderDetails(List.of(ClassBuilder.responseOrderDetailsDTOBuilder()));
		assertEquals(expectedResponseOrderDTO.toString(), responseOrderDTO.toString());
	}
	
}
