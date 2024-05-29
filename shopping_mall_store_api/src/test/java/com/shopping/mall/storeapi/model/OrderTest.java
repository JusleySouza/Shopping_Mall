package com.shopping.mall.storeapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class OrderTest {
	
	private Order expectedOrder;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedOrder = ClassBuilder.orderBuilder();
		expectedOrder.setId(id);
		expectedOrder.setIdUser(id);
	}

	@Test
	void builder() {
		Order order = Order.builder()
				.id(id)
				.idUser(id)
				.status(1)
				.total(1324.98)
				.created(LocalDate.now())
				.changed(LocalDate.now())
				.listOrderDetails(List.of(ClassBuilder.orderDetailsBuilder()))
				.build();
		assertEquals(expectedOrder.toString(), order.toString());
	}
	
	@Test
	void setter() {
		Order order = new Order();
		order.setId(id);
		order.setIdUser(id);
		order.setStatus(1);
		order.setTotal(1324.98);
		order.setCreated(LocalDate.now());
		order.setChanged(LocalDate.now());
		order.setListOrderDetails(List.of(ClassBuilder.orderDetailsBuilder()));
		assertEquals(expectedOrder.toString(), order.toString());
	}

}
