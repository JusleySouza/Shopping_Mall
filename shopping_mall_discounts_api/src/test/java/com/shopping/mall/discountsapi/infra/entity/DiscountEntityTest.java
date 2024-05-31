package com.shopping.mall.discountsapi.infra.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class DiscountEntityTest {

	private DiscountEntity expectedDiscountEntity;
	private UUID id;
	private UUID idObjectDiscount;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		idObjectDiscount = UUID.randomUUID();
		expectedDiscountEntity = ClassBuilder.discountEntityBuilder();
		expectedDiscountEntity.setId(id);
		expectedDiscountEntity.setIdObjectDiscount(idObjectDiscount);
	}

	@Test
	void builder() {
		DiscountEntity discountEntity = DiscountEntity.builder()
				.id(id)
				.typeDiscount("PROD")
				.idObjectDiscount(idObjectDiscount)
				.percentageDiscount(0.4)
				.created(LocalDate.now())
				.expiration(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
		assertEquals(expectedDiscountEntity.toString(), discountEntity.toString());
	}
	
	@Test
	void setter() {
		DiscountEntity discountEntity = new DiscountEntity();
		discountEntity.setId(id);
		discountEntity.setTypeDiscount("PROD");
		discountEntity.setIdObjectDiscount(idObjectDiscount);
		discountEntity.setPercentageDiscount(0.4);
		discountEntity.setCreated(LocalDate.now());
		discountEntity.setExpiration(LocalDate.now());
		discountEntity.setActive(Boolean.TRUE);
		assertEquals(expectedDiscountEntity.toString(), discountEntity.toString());
	}

}
