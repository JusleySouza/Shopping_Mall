package com.shopping.mall.storeapi.feignclients;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.mall.storeapi.model.dto.user.ResponseUserDTO;

@FeignClient(name = "shopping-user-api", path = "/users", decode404 = true)
public interface UserFeign {
	 
	@GetMapping("/{userId}")
	public ResponseEntity<ResponseUserDTO> findById (@PathVariable("userId") UUID userId);

}
