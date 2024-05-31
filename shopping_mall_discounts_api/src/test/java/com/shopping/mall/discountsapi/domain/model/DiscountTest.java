package com.shopping.mall.discountsapi.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class DiscountTest {

	private Discount expectedDiscount;
	private UUID id;
	private UUID idObjectDiscount;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		idObjectDiscount = UUID.randomUUID();
		expectedDiscount = ClassBuilder.discountBuilder();
		expectedDiscount.setId(id);
		expectedDiscount.setIdObjectDiscount(idObjectDiscount);
	}

	@Test
	void builder() {
		Discount discount = Discount.builder()
				.id(id)
				.typeDiscount("PROD")
				.idObjectDiscount(idObjectDiscount)
				.percentageDiscount(0.4)
				.created(LocalDate.now())
				.expiration(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
		assertEquals(expectedDiscount.toString(), discount.toString());
	}
	
	@Test
	void setter() {
		Discount discount = new Discount();
		discount.setId(id);
		discount.setTypeDiscount("PROD");
		discount.setIdObjectDiscount(idObjectDiscount);
		discount.setPercentageDiscount(0.4);
		discount.setCreated(LocalDate.now());
		discount.setExpiration(LocalDate.now());
		discount.setActive(Boolean.TRUE);
		assertEquals(expectedDiscount.toString(), discount.toString());
	}

}
