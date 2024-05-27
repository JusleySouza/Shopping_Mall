package com.shopping.mall.userapi.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.shopping.mall.userapi.model.dto.RequestUserDTO;
import com.shopping.mall.userapi.model.dto.ResponseUserDTO;
import com.shopping.mall.userapi.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServices services;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestUserDTO requestUserDTO){
		return services.create(requestUserDTO);
	}
	
	@GetMapping("/cpf/{userCpf}")
	public ResponseEntity<ResponseUserDTO> findByCpf (@PathVariable("userCpf") String userCpf){
		return new ResponseEntity<ResponseUserDTO>(services.findByCpf(userCpf), HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ResponseUserDTO>> findAll(){
		return new ResponseEntity<List<ResponseUserDTO>>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/name/{userName}")
	public ResponseEntity<ResponseUserDTO> findByName (@PathVariable("userName") String userName){
		return new ResponseEntity<ResponseUserDTO>(services.findByName(userName), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<ResponseUserDTO> findById (@PathVariable("userId") UUID userId){
		return new ResponseEntity<ResponseUserDTO>(services.findById(userId), HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Object> update(@RequestBody RequestUserDTO requestUserDTO,
			@PathVariable("userId") UUID userId) {
		return services.update(requestUserDTO, userId);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> delete(@PathVariable("userId") UUID userId){
		services.delete(userId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{userId}")
	public ResponseEntity<Object> reactivate(@PathVariable("userId") UUID userId){
		services.reactivate(userId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}

}
