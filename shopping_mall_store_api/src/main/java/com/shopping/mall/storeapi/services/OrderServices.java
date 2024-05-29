package com.shopping.mall.storeapi.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.mall.storeapi.model.dto.ListResponseOrderDTO;
import com.shopping.mall.storeapi.model.dto.RequestOrderDTO;

@Service
public interface OrderServices {
	
	public ResponseEntity<Object> create(RequestOrderDTO requestOrderDTO);
	
	public ListResponseOrderDTO findByIdUser(UUID idUser);
	
	public ListResponseOrderDTO findByStatus(int status);
	
	public ListResponseOrderDTO findByIdOrder(UUID idOrder);
	
	public ResponseEntity<Object> delete(UUID id);

}
