package com.shopping.mall.productsapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class CategoryTest {
	
	private Category expectedCategory;
	private UUID id;
	private Product product;
	private SubCategory subCategory;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		product = ClassBuilder.productBuilder();
		subCategory = ClassBuilder.subCategoryBuilder();
		expectedCategory = ClassBuilder.categoryBuilder();
		expectedCategory.setId(id);
		expectedCategory.setListProduct(List.of(product));
		expectedCategory.setListSubCategory(List.of(subCategory));
	}

	@Test
	void builder() {
		Category category = Category.builder()
				.id(id)
				.nameCategory("eletronicos")
				.listProduct(List.of(product))
				.listSubCategory(List.of(subCategory))
				.build();
		assertEquals(expectedCategory.toString(), category.toString());
	}
	
	@Test
	void setter() {
		Category category = new Category();
		category.setId(id);
		category.setNameCategory("eletronicos");
		category.setListProduct(List.of(product));	
		category.setListSubCategory(List.of(subCategory));
		assertEquals(expectedCategory.toString(), category.toString());
	}

}
