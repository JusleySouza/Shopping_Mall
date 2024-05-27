package com.shopping.mall.userapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.mall.userapi.model.dto.AddressDTO;

@Service
public interface AddressServices {
	
	public ResponseEntity<Object> create(AddressDTO addressDTO, UUID userID);
	
	public ResponseEntity<Object> update(AddressDTO addressDTO, UUID addressId);
	
	public ResponseEntity<Object> delete(UUID id);
	
	public AddressDTO findByIdAddress(UUID id);
	
	public List<AddressDTO> findByIdUser(UUID id);

}
