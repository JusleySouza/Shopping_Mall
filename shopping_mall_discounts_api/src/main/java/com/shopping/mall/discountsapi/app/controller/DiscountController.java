package com.shopping.mall.discountsapi.app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.mall.discountsapi.app.dto.RequestDiscountDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseDiscountDTO;
import com.shopping.mall.discountsapi.app.service.DiscountService;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/discounts")
public class DiscountController {
	
	private final DiscountService services;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Object> saveDiscount(@RequestBody RequestDiscountDTO requestDiscountDTO){
		return services.saveDiscount(requestDiscountDTO);
	}
	
	@PutMapping("/{discountId}")
	public ResponseEntity<Object> updateDiscount(@RequestBody RequestDiscountDTO requestDiscountDTO,
			@PathVariable("discountId") UUID discountId) {
		return services.updateDiscount(requestDiscountDTO, discountId);
	}
	
	@GetMapping("/types/{typeDiscount}")
	public ResponseEntity<List<ResponseDiscountDTO>> findAllTypeDiscount(@PathVariable("typeDiscount") String typeDiscount){
		return new ResponseEntity<List<ResponseDiscountDTO>>(services.findAllTypeDiscount(typeDiscount), HttpStatus.OK);
	}
	
	@GetMapping("/{discountId}")
	public ResponseEntity<ResponseDiscountDTO> findById (@PathVariable("discountId") UUID discountId){
		return new ResponseEntity<ResponseDiscountDTO>(services.findByIdDiscount(discountId), HttpStatus.OK);
	}
	
	@PutMapping("/deactivate/{discountId}")
	public ResponseEntity<Object> deactivateDiscount(@PathVariable("discountId") UUID discountId){
		services.deactivateDiscount(discountId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/activate/{discountId}")
	public ResponseEntity<Object> activateDiscount(@PathVariable("discountId") UUID discountId){
		services.activateDiscount(discountId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}

}
