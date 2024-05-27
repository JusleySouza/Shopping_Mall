package com.shopping.mall.productsapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class SubCategoryTest {
	
	private SubCategory expectedSubCategory;
	private UUID id;
	private Product product;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		product = ClassBuilder.productBuilder();
		expectedSubCategory = ClassBuilder.subCategoryBuilder();
		expectedSubCategory.setId(id);
		expectedSubCategory.setListProducts(List.of(product));
	}

	@Test
	void builder() {
		SubCategory subCategory = SubCategory.builder()
				.id(id)
				.nameSubCategory("televisores")
				.description("novos, semi-novos, com nota fiscal")
				.listProducts(List.of(product))
				.build();
		assertEquals(expectedSubCategory.toString(), subCategory.toString());
	}
	
	@Test
	void setter() {
		SubCategory subCategory = new SubCategory();
		subCategory.setId(id);
		subCategory.setNameSubCategory("televisores");
		subCategory.setDescription("novos, semi-novos, com nota fiscal");
		subCategory.setCategory(null);
		subCategory.setListProducts(List.of(product));
		assertEquals(expectedSubCategory.toString(), subCategory.toString());
	}

}
