package com.shopping.mall.productsapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.productsapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
	Category findByNameCategory(String nameCategory);

}
