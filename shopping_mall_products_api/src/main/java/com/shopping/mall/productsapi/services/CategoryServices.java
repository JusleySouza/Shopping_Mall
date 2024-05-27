package com.shopping.mall.productsapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.mall.productsapi.model.dto.RequestCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseCategoryDTO;

@Service
public interface CategoryServices {
	
	public ResponseEntity<Object> create(RequestCategoryDTO requestCategoryDTO);
	
	public List<ResponseCategoryDTO>findAll();
	
	public ResponseCategoryDTO findById(UUID id);
	
	public ResponseEntity<Object> update(RequestCategoryDTO requestCategoryDTO, UUID categoryId);
	
	public ResponseEntity<Object> delete(UUID id);

}
