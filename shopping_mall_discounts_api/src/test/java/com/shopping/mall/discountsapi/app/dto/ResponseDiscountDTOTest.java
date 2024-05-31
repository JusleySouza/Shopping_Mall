package com.shopping.mall.discountsapi.app.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.discountsapi.app.enums.TypeDiscount;
import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class ResponseDiscountDTOTest {

	private ResponseDiscountDTO expectedResponseDiscountDTO;
	private UUID id;
	private UUID idObjectDiscount;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		idObjectDiscount = UUID.randomUUID();
		expectedResponseDiscountDTO = ClassBuilder.responseDiscountDTOBuilder();
		expectedResponseDiscountDTO.setId(id);
		expectedResponseDiscountDTO.setIdObjectDiscount(idObjectDiscount);
	}

	@Test
	void builder() {
		ResponseDiscountDTO responseDiscountDTO = ResponseDiscountDTO.builder()
				.id(id)
				.typeDiscount(TypeDiscount.PROD)
				.idObjectDiscount(idObjectDiscount)
				.percentageDiscount(0.4)
				.created("2023-08-28")
				.expiration("2024-08-28")
				.active(Boolean.TRUE)
				.build();
		assertEquals(expectedResponseDiscountDTO.toString(), responseDiscountDTO.toString());
	}
	
	@Test
	void setter() {
		ResponseDiscountDTO responseDiscountDTO = new ResponseDiscountDTO();
		responseDiscountDTO.setId(id);
		responseDiscountDTO.setTypeDiscount(TypeDiscount.PROD);
		responseDiscountDTO.setIdObjectDiscount(idObjectDiscount);
		responseDiscountDTO.setPercentageDiscount(0.4);
		responseDiscountDTO.setCreated("2023-08-28");
		responseDiscountDTO.setExpiration("2024-08-28");
		responseDiscountDTO.setActive(Boolean.TRUE);
		assertEquals(expectedResponseDiscountDTO.toString(), responseDiscountDTO.toString());
	}

}
