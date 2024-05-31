package com.shopping.mall.discountsapi.infra.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class CouponEntityTest {

	private CouponEntity expectedCouponEntity;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedCouponEntity = ClassBuilder.couponEntityBuilder();
		expectedCouponEntity.setId(id);
	}

	@Test
	void builder() {
		CouponEntity couponEntity = CouponEntity.builder()
				.id(id)
				.code("30OFF")
				.percentage(0.3)
				.minQuantityProducts(4)
				.minOrderValue(15.99)
				.maxDiscount(450.99)
				.created(LocalDate.now())
				.expiration(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
		assertEquals(expectedCouponEntity.toString(), couponEntity.toString());
	}
	
	@Test
	void setter() {
		CouponEntity couponEntity = new CouponEntity();
		couponEntity.setId(id);
		couponEntity.setCode("30OFF");
		couponEntity.setPercentage(0.3);
		couponEntity.setMinQuantityProducts(4);
		couponEntity.setMinOrderValue(15.99);
		couponEntity.setMaxDiscount(450.99);
		couponEntity.setCreated(LocalDate.now());
		couponEntity.setExpiration(LocalDate.now());
		couponEntity.setActive(Boolean.TRUE);
		assertEquals(expectedCouponEntity.toString(), couponEntity.toString());
	}

}
