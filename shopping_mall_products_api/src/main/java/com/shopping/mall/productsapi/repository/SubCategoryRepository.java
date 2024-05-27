package com.shopping.mall.productsapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.productsapi.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {

	SubCategory findByNameSubCategory(String nameSubCategory);
	
	List<SubCategory> findByCategoryId(UUID id);
	
}
