package com.shopping.mall.discountsapi.app.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class RequestCouponDTOTest {

	private RequestCouponDTO expectedRequestCouponDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedRequestCouponDTO = ClassBuilder.requestCouponDTOBuilder();
	}

	@Test
	void builder() {
		RequestCouponDTO requestCouponDTO = RequestCouponDTO.builder()
				.code("30OFF")
				.percentage(0.3)
				.minQuantityProducts(4)
				.minOrderValue(15.99)
				.maxDiscount(450.99)
				.expiration(LocalDate.now())
				.build();
		assertEquals(expectedRequestCouponDTO.toString(), requestCouponDTO.toString());
	}
	
	@Test
	void setter() {
		RequestCouponDTO requestCouponDTO = new RequestCouponDTO();
		requestCouponDTO.setCode("30OFF");
		requestCouponDTO.setPercentage(0.3);
		requestCouponDTO.setMinQuantityProducts(4);
		requestCouponDTO.setMinOrderValue(15.99);
		requestCouponDTO.setMaxDiscount(450.99);
		requestCouponDTO.setExpiration(LocalDate.now());
		assertEquals(expectedRequestCouponDTO.toString(), requestCouponDTO.toString());
	}

}
