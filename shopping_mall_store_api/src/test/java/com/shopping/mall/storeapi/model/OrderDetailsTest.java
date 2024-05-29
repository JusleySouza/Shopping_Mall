package com.shopping.mall.storeapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class OrderDetailsTest {
	
	private OrderDetails expectedOrderDetails;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedOrderDetails = ClassBuilder.orderDetailsBuilder();
		expectedOrderDetails.setId(id);
		expectedOrderDetails.setIdProduct(id);
	}

	@Test
	void builder() {
		OrderDetails orderDetails = OrderDetails.builder()
				.id(id)
				.idProduct(id)
				.amount(3)
				.subtotal(1324.98)
				.build();
		assertEquals(expectedOrderDetails.toString(), orderDetails.toString());
	}
	
	@Test
	void setter() {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setId(id);
		orderDetails.setIdProduct(id);
		orderDetails.setAmount(3);
		orderDetails.setSubtotal(1324.98);
		assertEquals(expectedOrderDetails.toString(), orderDetails.toString());
	}

}
