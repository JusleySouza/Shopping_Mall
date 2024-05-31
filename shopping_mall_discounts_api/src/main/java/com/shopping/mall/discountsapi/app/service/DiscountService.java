package com.shopping.mall.discountsapi.app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.mall.discountsapi.app.dto.RequestDiscountDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseDiscountDTO;

@Service
public interface DiscountService {
	
	public ResponseEntity<Object> saveDiscount(RequestDiscountDTO requestDiscountDTO);
	
	public ResponseEntity<Object> updateDiscount(RequestDiscountDTO requestDiscountDTO, UUID discountId);
	
	public List<ResponseDiscountDTO>findAllTypeDiscount(String typeDiscount);
	
	public ResponseDiscountDTO findByIdDiscount(UUID id);
	
	public ResponseEntity<ResponseDiscountDTO> deactivateDiscount(UUID discountId);
	
	public ResponseEntity<ResponseDiscountDTO> activateDiscount(UUID discountId);

}
