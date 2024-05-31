package com.shopping.mall.discountsapi.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class CouponTest {
	
	private Coupon expectedCoupon;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedCoupon = ClassBuilder.couponBuilder();
		expectedCoupon.setId(id);
	}

	@Test
	void builder() {
		Coupon coupon = Coupon.builder()
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
		assertEquals(expectedCoupon.toString(), coupon.toString());
	}
	
	@Test
	void setter() {
		Coupon coupon = new Coupon();
		coupon.setId(id);
		coupon.setCode("30OFF");
		coupon.setPercentage(0.3);
		coupon.setMinQuantityProducts(4);
		coupon.setMinOrderValue(15.99);
		coupon.setMaxDiscount(450.99);
		coupon.setCreated(LocalDate.now());
		coupon.setExpiration(LocalDate.now());
		coupon.setActive(Boolean.TRUE);
		assertEquals(expectedCoupon.toString(), coupon.toString());
	}

}
