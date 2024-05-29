package com.shopping.mall.storeapi.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.shopping.mall.storeapi.exception.ResourceNotFoundException;
import com.shopping.mall.storeapi.exception.ValidationException;
import com.shopping.mall.storeapi.feignclients.ProductFeign;
import com.shopping.mall.storeapi.feignclients.UserFeign;
import com.shopping.mall.storeapi.model.Order;
import com.shopping.mall.storeapi.model.dto.ListResponseOrderDTO;
import com.shopping.mall.storeapi.model.dto.RequestOrderDTO;
import com.shopping.mall.storeapi.model.dto.ResponseOrderDTO;
import com.shopping.mall.storeapi.model.dto.product.ResponseProductDTO;
import com.shopping.mall.storeapi.model.dto.user.ResponseUserDTO;
import com.shopping.mall.storeapi.repository.OrderDetailsRepository;
import com.shopping.mall.storeapi.repository.OrderRepository;
import com.shopping.mall.storeapi.utils.ClassBuilder;


class OrderServicesImplementTest {
	
	@InjectMocks
	private OrderServicesImplement services;
	
	@Mock
	private ProductFeign productFeign;
	
	@Mock
	private UserFeign userFeign;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private OrderRepository repository;
	
	@Mock
	private OrderDetailsRepository orderDetailsRepository;
	
	private Order order;
	private RequestOrderDTO requestOrderDTO;
	private ResponseEntity<Object> product;
	private ResponseOrderDTO responseOrderDTO;
	private ResponseEntity<ResponseProductDTO> responseProductDTOEntity;
	private ResponseProductDTO responseProductDTO;
	private ResponseEntity<ResponseUserDTO> responseUserDTOEntity;
	private ResponseUserDTO responseUserDTO;
	private ListResponseOrderDTO listResponseOrderDTO;
	private LocalValidatorFactoryBean validator;
	private UUID id;
	private List<Order> listOrder;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = UUID.randomUUID();
		order = ClassBuilder.orderBuilder();
		requestOrderDTO = ClassBuilder.requestOrderDTOBuilder();
		responseOrderDTO = ClassBuilder.responseOrderDTOBuilder();
		responseProductDTO = ClassBuilder.responseProductDTOBuilder();
		responseProductDTOEntity = new ResponseEntity<ResponseProductDTO>(responseProductDTO, HttpStatus.OK);
		responseUserDTO = ClassBuilder.responseUserDTOBuilder();
		responseUserDTOEntity = new ResponseEntity<ResponseUserDTO>(responseUserDTO, HttpStatus.OK);
		listResponseOrderDTO = ClassBuilder.listResponseOrderDTOBuilder();
		listOrder = List.of(order);
		product = new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		
		
		order.setId(id);
		responseOrderDTO.setId(id);
		order.setListOrderDetails(List.of(ClassBuilder.orderDetailsBuilder()));
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
	}

	@Test
	public void create() {
		when(productFeign.findById(any())).thenReturn(responseProductDTOEntity);
		when(productFeign.subtraction(any(), anyInt())).thenReturn(product);
		ResponseEntity<Object> order = services.create(requestOrderDTO);
		assertTrue(order.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test
	public void createNoHttpStatusNoContent() {
		String messageError = "Insufficient stock quantity of the product " + responseProductDTO.getName()
		+ ". Available quantity: " + responseProductDTO.getStock() + ".";
		when(productFeign.findById(any())).thenReturn(responseProductDTOEntity);
		when(productFeign.subtraction(any(), anyInt())).thenReturn(new ResponseEntity<Object>(
				"Insufficient stock quantity of the product televisão. Available quantity: 5.", HttpStatus.UNPROCESSABLE_ENTITY));
		
		String message = assertThrows(ValidationException.class, () -> {
			services.create(requestOrderDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void findByIduser() {
		when(userFeign.findById(any())).thenReturn(responseUserDTOEntity);
		when(repository.findByIdUser(any())).thenReturn(listOrder);
		when(productFeign.findById(any())).thenReturn(responseProductDTOEntity);
		listResponseOrderDTO = services.findByIdUser(id);
		assertNotNull(listResponseOrderDTO);
	}
	
	@Test
	public void findByIdUserResourceNotFoundException() {
		String messageError = "Sorry, it was not possible to return the order list. "
				+ "The specified user does not exist.";
		when(userFeign.findById(any())).thenReturn(new ResponseEntity<ResponseUserDTO>(HttpStatus.NOT_FOUND));
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByIdUser(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void findByStatus() throws Exception{
		when(repository.findByStatus(anyInt())).thenReturn(listOrder);
		when(productFeign.findById(any())).thenReturn(responseProductDTOEntity);
		listResponseOrderDTO = services.findByStatus(1);
		assertNotNull(listResponseOrderDTO);
	}
	
	@Test
	public void statusResourceNotFoundException() {
		String messageError = "Sorry, we couldn't find orders with that status.";
		
		when(repository.findByStatus(anyInt())).thenReturn(listOrder);
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByStatus(6);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void statusResourceNotFoundException2() {
		String messageError = "Sorry, we couldn't find orders with that status.";
		
		when(repository.findByStatus(anyInt())).thenReturn(listOrder);
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByStatus(0);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void findByIdOrder() throws Exception{
		when(productFeign.findById(any())).thenReturn(responseProductDTOEntity);
		when(repository.findById(any())).thenReturn(Optional.of(order));
		listResponseOrderDTO = services.findByIdOrder(id);
		assertNotNull(listResponseOrderDTO);
	}

	
	@Test
	public void findByIdOrderResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find this order.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByIdOrder(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void delete() {
		when(repository.findById(any())).thenReturn(Optional.of(order));
		when(productFeign.addition(any(), anyInt())).thenReturn(product);
		ResponseEntity<Object>  result = services.delete(any());
		assertTrue(result.getStatusCode().equals( HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void deleteResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find this order.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void deleteValidationException() {
		String messageError = "This order is already cancelled.";
		
		order.setStatus(5);
		when(repository.findById(any())).thenReturn(Optional.of(order));
				
		String message = assertThrows(ValidationException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void deleteValidationException2() {
		String messageError = "Order cannot be canceled";
		
		order.setStatus(2);
		when(repository.findById(any())).thenReturn(Optional.of(order));
				
		String message = assertThrows(ValidationException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void deleteValidationException3() {
		String messageError = "There are no products to return to stock.";
		
		when(repository.findById(any())).thenReturn(Optional.of(order));
		when(productFeign.addition(any(), anyInt())).thenReturn(new ResponseEntity<Object>(
				"There are no products to return to stock.", HttpStatus.UNPROCESSABLE_ENTITY));
				
		String message = assertThrows(ValidationException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
}
