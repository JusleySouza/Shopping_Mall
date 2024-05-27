package com.shopping.mall.productsapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.mall.productsapi.model.dto.RequestSubCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSubCategoryDTO;

@Service
public interface SubCategoryServices {
	
	public ResponseEntity<Object> create(RequestSubCategoryDTO requestSubCategoryDTO);
	
	public List<ResponseSubCategoryDTO>findAll();
	
	public ResponseSubCategoryDTO findById(UUID id);
	
	public ResponseEntity<Object> update(RequestSubCategoryDTO requestSubCategoryDTO, UUID subCategoryId);
	
	public ResponseEntity<Object> delete(UUID id);
	
	public List<ResponseSubCategoryDTO> findByCategory(UUID id);

}
