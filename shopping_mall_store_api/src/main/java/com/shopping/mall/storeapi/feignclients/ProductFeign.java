package com.shopping.mall.storeapi.feignclients;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.mall.storeapi.model.dto.product.ResponseProductDTO;

@FeignClient(name = "shopping-product-api", path = "/products", decode404 = true)
public interface ProductFeign {
	
	@GetMapping("/{productId}")
	public ResponseEntity<ResponseProductDTO> findById (@PathVariable("productId") UUID productId);
	
	@PutMapping("/subtraction/{productId}")
	public ResponseEntity<Object> subtraction(@PathVariable("productId") UUID productId, @RequestParam("amount") int amount);
	
	@PutMapping("/addition/{productId}")
	public ResponseEntity<Object> addition(@PathVariable("productId") UUID productId, @RequestParam("amount") int amount);

}
