package com.shopping.mall.productsapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>{
	
	Product findByIdAndActiveTrue(UUID id);
	
	Product findByName(String name);
	
	Product findBySku(int sku);
	
	Product findByCategory(Category category);

	List<Product> findByCategoryId(UUID id);
	
	List<Product> findBySubCategoryId(UUID id);
	
	List<Product> findAllByActiveTrue();

}
