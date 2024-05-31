package com.shopping.mall.authenticationapi.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.mall.authenticationapi.model.dto.LoginDTO;
import com.shopping.mall.authenticationapi.model.dto.UserCredentialDTO;
import com.shopping.mall.authenticationapi.model.dto.UserCredentialRegistroDTO;
import com.shopping.mall.authenticationapi.services.AuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@PostMapping("/admin")
	public ResponseEntity<UserCredentialDTO> createAdmin(@RequestBody UserCredentialRegistroDTO dto){
		log.info("Creating Admin");
		UserCredentialDTO admin = service.createAdmin(dto);
		log.info("Admin created with success");
		return ResponseEntity.ok(admin);
	}
	

	@PostMapping("/users")
	public ResponseEntity<UserCredentialDTO> createUser(@RequestBody UserCredentialRegistroDTO dto){
		log.info("Creating User");
		UserCredentialDTO user = service.createUser(dto);
		log.info("User created with success");
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto) throws AuthenticationException{
		log.info("Login created with success");
		return ResponseEntity.ok(service.login(dto));
	}
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		return service.validateToken(token);
	}
	
	
	
}
