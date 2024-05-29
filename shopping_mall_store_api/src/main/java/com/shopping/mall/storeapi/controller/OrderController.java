package com.shopping.mall.storeapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.mall.storeapi.model.dto.ListResponseOrderDTO;
import com.shopping.mall.storeapi.model.dto.RequestOrderDTO;
import com.shopping.mall.storeapi.services.OrderServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping(value = "/orders")
@CircuitBreaker(name = "backendA")
public class OrderController {
	
	@Autowired
	private OrderServices services;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestOrderDTO requestOrderDTO){
		return services.create(requestOrderDTO);
	}
	
	@GetMapping("/user/{idUser}")
	public ResponseEntity<ListResponseOrderDTO> findByIdUser(@PathVariable("idUser") UUID idUser){
		return new ResponseEntity<ListResponseOrderDTO>(services.findByIdUser(idUser), HttpStatus.OK);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ListResponseOrderDTO> findByStatus(@PathVariable("status") int status){
		return new ResponseEntity<ListResponseOrderDTO>(services.findByStatus(status), HttpStatus.OK);
	}
	
	@GetMapping("/order/{idOrder}")
	public ResponseEntity<ListResponseOrderDTO> findByIdOrder(@PathVariable("idOrder") UUID idOrder){
		return new ResponseEntity<ListResponseOrderDTO>(services.findByIdOrder(idOrder), HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Object> delete(@PathVariable("orderId") UUID orderId){
		services.delete(orderId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}

}
