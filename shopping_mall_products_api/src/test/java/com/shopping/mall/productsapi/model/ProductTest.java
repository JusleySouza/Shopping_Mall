package com.shopping.mall.productsapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class ProductTest {
	private Product expectedProduct;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedProduct = ClassBuilder.productBuilder();
		expectedProduct.setId(id);
	}

	@Test
	void builder() {
		Product product = Product.builder()
				.id(id)
				.name("televisão")
				.description("produtos novos, portateis e de facil manuseio")
				.sku(123)
				.valueUnitary(1324.99)
				.stock(5)
				.created(LocalDate.now())
				.changed(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
		assertEquals(expectedProduct.toString(), product.toString());
	}
	
	
	@Test
	void setter() {
		Product product = new Product();
		product.setId(id);
		product.setName("televisão");
		product.setDescription("produtos novos, portateis e de facil manuseio");
		product.setSku(123);
		product.setValueUnitary(1324.99);
		product.setStock(5);
		product.setActive(Boolean.TRUE);
		product.setCreated(LocalDate.now());
		product.setChanged(LocalDate.now());
		product.setSubCategory(null);
		assertEquals(expectedProduct.toString(), product.toString());
	}

}
