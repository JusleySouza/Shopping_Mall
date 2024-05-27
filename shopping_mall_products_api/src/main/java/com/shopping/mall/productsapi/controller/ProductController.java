package com.shopping.mall.productsapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.mall.productsapi.model.dto.RequestProductDTO;
import com.shopping.mall.productsapi.model.dto.ResponseProductDTO;
import com.shopping.mall.productsapi.services.ProductServices;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductServices services;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestProductDTO requestProductDTO){
		return services.create(requestProductDTO);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ResponseProductDTO>> findAll(){
		return new ResponseEntity<List<ResponseProductDTO>>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ResponseProductDTO> findById (@PathVariable("productId") UUID productId){
		return new ResponseEntity<ResponseProductDTO>(services.findById(productId), HttpStatus.OK);
	}
	
	@GetMapping("/name/{productName}")
	public ResponseEntity<ResponseProductDTO> findByName (@PathVariable("productName") String productName){
		return new ResponseEntity<ResponseProductDTO>(services.findByName(productName), HttpStatus.OK);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<Object> update(@RequestBody RequestProductDTO requestProductDTO,
			@PathVariable("productId") UUID productId) {
		return services.update(requestProductDTO, productId);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<Object> delete(@PathVariable("productId") UUID productId){
		services.delete(productId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{productId}")
	public ResponseEntity<Object> reactivate(@PathVariable("productId") UUID productId){
		services.reactivate(productId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	
	@PutMapping("/subtraction/{productId}")
	public ResponseEntity<Object> subtraction(@PathVariable("productId") UUID productId, @RequestParam("amount") int amount){
		services.subtraction(productId, amount);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/addition/{productId}")
	public ResponseEntity<Object> addition(@PathVariable("productId") UUID productId, @RequestParam("amount") int amount){
		services.addition(productId, amount);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ResponseProductDTO>> findByCategory (@PathVariable("categoryId") UUID categoryId){
		return new ResponseEntity<List<ResponseProductDTO>>(services.findByCategory(categoryId), HttpStatus.OK);
	}
	
	@GetMapping("/subcategory/{subCategoryId}")
	public ResponseEntity<List<ResponseProductDTO>> findBySubCategory (@PathVariable("subCategoryId") UUID subCategoryId){
		return new ResponseEntity<List<ResponseProductDTO>>(services.findBySubCategory(subCategoryId), HttpStatus.OK);
	}

}
