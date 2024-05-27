package com.shopping.mall.userapi.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.shopping.mall.userapi.exception.ResourceNotFoundException;
import com.shopping.mall.userapi.exception.ValidationException;
import com.shopping.mall.userapi.model.Address;
import com.shopping.mall.userapi.model.User;
import com.shopping.mall.userapi.model.dto.AddressDTO;
import com.shopping.mall.userapi.repository.AddressRepository;
import com.shopping.mall.userapi.repository.UserRepository;
import com.shopping.mall.userapi.test.utils.ClassBuilder;

class AddressServicesImplementTest {
	
	@InjectMocks
	private AddressServicesImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private AddressRepository repository;
	
	@Mock
	private UserRepository userRepository;
	
	private User user;
	private Address address;
	private AddressDTO addressDTO;
	private List<Address> listAddress;
	private UUID id;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = UUID.randomUUID();
		user = ClassBuilder.userBuilder();
		address = ClassBuilder.addressBuilder();
		addressDTO = ClassBuilder.addressDTOBuilder();
		
		user.setId(id);
		user.setListAddress(List.of(ClassBuilder.addressBuilder()));
		
		address.setId(id);
		address.setUser(user);
		listAddress = List.of(address);
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		
	}

	@Test
	public void findByIdAddress() throws Exception{
		when(repository.findById(any())).thenReturn(Optional.of(address));
		AddressDTO addressId = services.findByIdAddress(id);
		assertEquals(addressId.toString(), addressDTO.toString());
	}
	
	@Test
	public void findByIdResourceNotFoundException() {
		String messageError = "Sorry, we could not find a address with this id. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByIdAddress(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void findByIdUser() throws Exception{
		when(repository.findByUserId(any())).thenReturn(listAddress);
		List<AddressDTO> listResponse = services.findByIdUser(id);
		assertEquals(listResponse.get(0).toString(), addressDTO.toString());
	}
	
	@Test
	public void create() {
		when(userRepository.findByIdAndActiveTrue(any())).thenReturn(user);
		ResponseEntity<Object> address = services.create(addressDTO, id);
		assertTrue(address.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test 
	public void createWithMissingFields() {
		when(userRepository.findByIdAndActiveTrue(any())).thenReturn(user);
		addressDTO.setCep(null);
		violations = validator.validate(addressDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> address = services.create(addressDTO, id);
		assertTrue(address.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@Test
	public void createListAddressIsEmpty() {
		user.setListAddress(List.of());
		when(userRepository.findByIdAndActiveTrue(any())).thenReturn(user);
		ResponseEntity<Object> address = services.create(addressDTO, id);
		assertTrue(address.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test
	public void createWithDuplicateNickname() {
		String messageError = "Unable to register address. "
				+ "There is already a registered address with this nickName. Check and try again.";
		when(repository.findByNickName(anyString())).thenReturn(address);
		String message = assertThrows(ValidationException.class, () -> {
			services.create(addressDTO, id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(address));
		when(userRepository.findByIdAndActiveTrue(any())).thenReturn(user);
		ResponseEntity<Object> address = services.update(addressDTO, id);
		assertTrue(address.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void updateDefaultAddress() {
		user.getListAddress().get(0).setId(id);
		when(repository.findById(any())).thenReturn(Optional.of(address));
		when(userRepository.findByIdAndActiveTrue(any())).thenReturn(user);
		ResponseEntity<Object> address = services.update(addressDTO, id);
		assertTrue(address.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "Sorry, we could not find a address with this id. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(addressDTO, id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void updateWithMissingFields() {
		addressDTO.setCep(null);
		violations = validator.validate(addressDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> address = services.update(addressDTO, id);
		assertTrue(address.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@Test
	public void delete() {
		when(repository.findById(any())).thenReturn(Optional.of(address));
		when(userRepository.findByIdAndActiveTrue(any())).thenReturn(user);
		ResponseEntity<Object> address = services.delete(id);
		assertTrue(address.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void deleteListAddress() {
		user.getListAddress().get(0).setId(id);
		when(repository.findById(any())).thenReturn(Optional.of(address));
		when(userRepository.findByIdAndActiveTrue(any())).thenReturn(user);
		
		listAddress = Arrays.asList(address); 
	       
        List<Address> list = new ArrayList<>(listAddress);
		
		list.add(address);
		list.remove(0);
		
		ResponseEntity<Object> address = services.delete(id);
		assertTrue(address.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void deleteResourceNotFoundException() {
		String messageError = "Sorry, we could not find a address with this id. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.delete(id);
		}).getMessage();
		assertEquals(messageError, message);
	}

}
