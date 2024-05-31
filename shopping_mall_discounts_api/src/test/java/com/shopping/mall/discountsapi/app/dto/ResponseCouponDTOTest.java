package com.shopping.mall.discountsapi.app.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class ResponseCouponDTOTest {

	private ResponseCouponDTO expectedResponseCouponDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseCouponDTO = ClassBuilder.responseCouponDTOBuilder();
		expectedResponseCouponDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseCouponDTO responseCouponDTO = ResponseCouponDTO.builder()
				.id(id)
				.code("30OFF")
				.percentage(0.3)
				.minQuantityProducts(4)
				.minOrderValue(15.99)
				.maxDiscount(450.99)
				.created("2023-08-28")
				.expiration("2024-09-28")
				.active(Boolean.TRUE)
				.build();
		assertEquals(expectedResponseCouponDTO.toString(), responseCouponDTO.toString());
	}
	
	@Test
	void setter() {
		ResponseCouponDTO responseCouponDTO = new ResponseCouponDTO();
		responseCouponDTO.setId(id);
		responseCouponDTO.setCode("30OFF");
		responseCouponDTO.setPercentage(0.3);
		responseCouponDTO.setMinQuantityProducts(4);
		responseCouponDTO.setMinOrderValue(15.99);
		responseCouponDTO.setMaxDiscount(450.99);
		responseCouponDTO.setCreated("2023-08-28");
		responseCouponDTO.setExpiration("2024-09-28");
		responseCouponDTO.setActive(Boolean.TRUE);
		assertEquals(expectedResponseCouponDTO.toString(), responseCouponDTO.toString());
	}

}
