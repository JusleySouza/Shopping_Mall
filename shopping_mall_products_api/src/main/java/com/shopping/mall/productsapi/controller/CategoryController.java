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

import com.shopping.mall.productsapi.model.dto.RequestCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseCategoryDTO;
import com.shopping.mall.productsapi.services.CategoryServices;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServices categoryServices;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestCategoryDTO requestCategoryDTO){
		return categoryServices.create(requestCategoryDTO);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ResponseCategoryDTO>> findAll(){
		return new ResponseEntity<List<ResponseCategoryDTO>>(categoryServices.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<ResponseCategoryDTO> findById (@PathVariable("categoryId") UUID categoryId){
		return new ResponseEntity<ResponseCategoryDTO>(categoryServices.findById(categoryId), HttpStatus.OK);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<Object> update(@RequestBody RequestCategoryDTO requestCategoryDTO,
			@PathVariable("categoryId") UUID categoryId) {
		return categoryServices.update(requestCategoryDTO, categoryId);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Object> delete(@PathVariable("categoryId") UUID categoryId){
		return categoryServices.delete(categoryId); 
	}

}
