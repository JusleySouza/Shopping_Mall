package com.shopping.mall.discountsapi.app.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.discountsapi.app.enums.TypeDiscount;
import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class RequestDiscountDTOTest {

	private RequestDiscountDTO expectedRequestDiscountDTO;
	private UUID idObjectDiscount;

	@BeforeEach
	void setUp() throws Exception {
		idObjectDiscount = UUID.randomUUID();
		expectedRequestDiscountDTO = ClassBuilder.requestDiscountDTOBuilder();
		expectedRequestDiscountDTO.setIdObjectDiscount(idObjectDiscount);
	}

	@Test
	void builder() {
		RequestDiscountDTO requestDiscountDTO = RequestDiscountDTO.builder()
				.typeDiscount(TypeDiscount.PROD)
				.idObjectDiscount(idObjectDiscount)
				.percentageDiscount(0.4)
				.expiration(LocalDate.now())
				.build();
		assertEquals(expectedRequestDiscountDTO.toString(), requestDiscountDTO.toString());
	}
	
	@Test
	void setter() {
		RequestDiscountDTO requestDiscountDTO = new RequestDiscountDTO();
		requestDiscountDTO.setTypeDiscount(TypeDiscount.PROD);
		requestDiscountDTO.setIdObjectDiscount(idObjectDiscount);
		requestDiscountDTO.setPercentageDiscount(0.4);
		requestDiscountDTO.setExpiration(LocalDate.now());
		assertEquals(expectedRequestDiscountDTO.toString(), requestDiscountDTO.toString());
	}

}
