package com.shopping.mall.productsapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.mall.productsapi.model.Product;
import com.shopping.mall.productsapi.model.dto.RequestProductDTO;
import com.shopping.mall.productsapi.model.dto.ResponseProductDTO;

@Service
public interface ProductServices {
	
	public ResponseEntity<Object> create(RequestProductDTO requestProductDTO);
	
	public List<ResponseProductDTO>findAll();
	
	public ResponseProductDTO findById(UUID id);
	
	public ResponseProductDTO findByName(String name);
	
	public ResponseEntity<Object> update(RequestProductDTO requestProductDTO, UUID productId);
	
	public Product delete(UUID id);
	
	public List<ResponseProductDTO> findByCategory(UUID id);
	
	public List<ResponseProductDTO> findBySubCategory(UUID id);
	
	public void subtraction(UUID productId, int amount);
	
	public void addition(UUID productId, int amount);
	
	public ResponseEntity<ResponseProductDTO> reactivate(UUID productId);

}
