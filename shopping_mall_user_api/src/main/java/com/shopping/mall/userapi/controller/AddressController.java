package com.shopping.mall.userapi.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.mall.userapi.model.dto.AddressDTO;
import com.shopping.mall.userapi.services.AddressServices;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressServices services;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody AddressDTO addressDTO, @RequestParam UUID userID){
		return new ResponseEntity<Object>(services.create(addressDTO, userID), HttpStatus.CREATED);
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<Object> update(@RequestBody AddressDTO addressDTO,
			@PathVariable("addressId") UUID addressId) {
		return new ResponseEntity<Object>(services.update(addressDTO, addressId), HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<Object> delete(@PathVariable("addressId") UUID addressId){
		return new ResponseEntity<Object>(services.delete(addressId), HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<AddressDTO> findByIdAddress (@PathVariable("addressId") UUID addressId){
		return new ResponseEntity<AddressDTO>(services.findByIdAddress(addressId), HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<AddressDTO>> findByIdUser (@PathVariable("userId") UUID userId){
		return new ResponseEntity<List<AddressDTO>>(services.findByIdUser(userId), HttpStatus.OK);
	}

}
