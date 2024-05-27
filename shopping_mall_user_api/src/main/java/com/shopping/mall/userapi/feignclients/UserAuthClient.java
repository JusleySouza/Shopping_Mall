package com.shopping.mall.userapi.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "shopping-authentication-api", url = "localhost:8300", path = "/auth")
public interface UserAuthClient {
	
	@PostMapping("/users")
	public ResponseEntity<UserCredentialDTO> createUser(@RequestBody UserCredentialDTO dto);

}
