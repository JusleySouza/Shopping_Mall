package com.shopping.mall.userapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.mall.userapi.model.User;
import com.shopping.mall.userapi.model.dto.RequestUserDTO;
import com.shopping.mall.userapi.model.dto.ResponseUserDTO;

@Service
public interface UserServices {
	
	public ResponseEntity<Object> create(RequestUserDTO requestUserDTO);
	
	public ResponseUserDTO findByCpf(String cpf);
	
	public List<ResponseUserDTO>findAll();
	
	public ResponseUserDTO findByName(String name);
	
	public ResponseUserDTO findById(UUID id);
	
	public ResponseEntity<Object> update(RequestUserDTO requestUserDTO, UUID userId);
	
	public User delete(UUID id);
	
	public ResponseEntity<ResponseUserDTO> reactivate(UUID userId);

}
