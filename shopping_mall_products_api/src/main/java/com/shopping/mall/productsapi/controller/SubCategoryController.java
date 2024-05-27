package com.shopping.mall.productsapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.mall.productsapi.model.dto.RequestSubCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSubCategoryDTO;
import com.shopping.mall.productsapi.services.SubCategoryServices;

@RestController
@RequestMapping("/sub_category")
public class SubCategoryController {
	
	@Autowired
	private SubCategoryServices services;

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestSubCategoryDTO requestSubCategoryDTO){
		return services.create(requestSubCategoryDTO);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ResponseSubCategoryDTO>> findAll(){
		return new ResponseEntity<List<ResponseSubCategoryDTO>>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{subCategoryId}")
	public ResponseEntity<ResponseSubCategoryDTO> findById (@PathVariable("subCategoryId") UUID subCategoryId){
		return new ResponseEntity<ResponseSubCategoryDTO>(services.findById(subCategoryId), HttpStatus.OK);
	}
	
	@PutMapping("/{subCategoryId}")
	public ResponseEntity<Object> update(@RequestBody RequestSubCategoryDTO requestSubCategoryDTO,
			@PathVariable("subCategoryId") UUID subCategoryId) {
		return services.update(requestSubCategoryDTO, subCategoryId);
	}
	
	@DeleteMapping("/{subCategoryId}")
	public ResponseEntity<Object> delete(@PathVariable("subCategoryId") UUID subCategoryId){
		return services.delete(subCategoryId);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ResponseSubCategoryDTO>> findByCategory (@PathVariable("categoryId") UUID categoryId){
		return new ResponseEntity<List<ResponseSubCategoryDTO>>(services.findByCategory(categoryId), HttpStatus.OK);
	}
	
}
