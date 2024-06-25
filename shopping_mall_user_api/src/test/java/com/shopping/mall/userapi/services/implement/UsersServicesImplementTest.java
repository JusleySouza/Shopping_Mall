package com.shopping.mall.userapi.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

import com.shopping.mall.userapi.exception.DuplicateDocumentsException;
import com.shopping.mall.userapi.exception.ResourceNotFoundException;
import com.shopping.mall.userapi.exception.UpdateNotAllowedException;
import com.shopping.mall.userapi.exception.ValidationException;
import com.shopping.mall.userapi.feignclients.UserAuthClient;
import com.shopping.mall.userapi.model.User;
import com.shopping.mall.userapi.model.dto.RequestUserDTO;
import com.shopping.mall.userapi.model.dto.ResponseUserDTO;
import com.shopping.mall.userapi.repository.UserRepository;
import com.shopping.mall.userapi.test.utils.ClassBuilder;

class UsersServicesImplementTest {
	
	@InjectMocks
	private UsersServicesImplement services;
	
	@Mock
	private UserAuthClient userAuthClient;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private UserRepository repository;
	
	private User user;
	private RequestUserDTO requestUserDTO;
	private ResponseUserDTO responseUserDTO;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;
	private List<ResponseUserDTO> listResponse;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = UUID.randomUUID();
		user = ClassBuilder.userBuilder();
		requestUserDTO = ClassBuilder.requestUserDTOBuilder();
		responseUserDTO = ClassBuilder.responseUserDTOBuilder();
		
		user.setId(id);
		responseUserDTO.setId(id);
		user.setListAddress(List.of(ClassBuilder.addressBuilder()));
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
	}

	@Test
	public void findByCpf() throws Exception{
		when(repository.findByCpfAndActiveTrue(anyString())).thenReturn(user);
		ResponseUserDTO userCpf = services.findByCpf("111.300.458-46");
		assertEquals(userCpf.toString(), responseUserDTO.toString());
	}
	
	
	@Test
	public void findByCpfResourceNotFoundException() {
		String messageError = "Sorry, we could not find a user with this cpf. Check and try again.!";
		
		when(repository.findByCpfAndActiveTrue(anyString())).thenReturn(null);
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByCpf("11.300.458-46");
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void findByName() throws Exception{
		when(repository.findByFullName(anyString())).thenReturn(user);
		ResponseUserDTO userName = services.findByName("Paulo");
		assertEquals(userName.toString(), responseUserDTO.toString());
	}
	
	
	@Test
	public void findByNameResourceNotFoundException() {
		String messageError = "Could not find a user with this name. Check and try again.";
		
		when(repository.findByFullName(anyString())).thenReturn(null);
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByName("Paulo");
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void findByNameValidationException() {
		String messageError = "Sorry, it was not possible to search by name. The name entered must be at least 2 characters long.";
		
		when(repository.findByFullName(anyString())).thenReturn(null);
		
		String message = assertThrows(ValidationException.class, () -> {
			services.findByName("P");
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void findAll() {
		when(repository.findAllByActiveTrue()).thenReturn(List.of(user));
		listResponse = services.findAll();
		assertNotNull(listResponse);
	}
	
	@Test
	public void findById() throws Exception{
		when(repository.findByIdAndActiveTrue(any())).thenReturn(user);
		ResponseUserDTO userId = services.findById(id);
		assertEquals(userId.toString(), responseUserDTO.toString());
	}
	
	@Test
	public void findByIdResourceNotFoundException() {
		String messageError = "Sorry, we could not find a user with this id. Check and try again.";
		
		when(repository.findByIdAndActiveTrue(any())).thenReturn(null);
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findById(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void delete() {
		when(repository.findById(any())).thenReturn(Optional.of(user));
		User users = services.delete(id);
		assertTrue(users.getActive().equals(false));
	}
	
	@Test
	public void deleteResourceNotFoundException() {
		String messageError = "Sorry, we could not find a user with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void deleteValidationException() {
		String messageError = "This user already has their account disabled.";
		
		user.setActive(false);
		
		when(repository.findById(any())).thenReturn(Optional.of(user));
		
		String message = assertThrows(ValidationException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void create() {
		ResponseEntity<Object> user = services.create(requestUserDTO);
		assertTrue(user.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test 
	public void createWithMissingFields() {
		requestUserDTO.setCpf(null);
		
		violations = validator.validate(requestUserDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> user = services.create(requestUserDTO);
		
		assertTrue(user.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		
	}
	
	@Test
	public void createWithDuplicateCpf() {
		String messageError = "Unable to register user. There is already a registered user with this CPF. Check and try again.";
		when(repository.findByCpfAndActiveTrue(anyString())).thenReturn(user);
		
		String message = assertThrows(DuplicateDocumentsException.class, () -> {
			services.create(requestUserDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(user));
		ResponseEntity<Object> user = services.update(requestUserDTO, id);
		assertTrue(user.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "Sorry, we could not find a user with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestUserDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void updateWithMissingFields() {
		requestUserDTO.setFullName(null);
		
		violations = validator.validate(requestUserDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> user = services.update(requestUserDTO, id);
		
		assertTrue(user.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		
	}
	
	@Test
	public void updateNotAllowedException() {
		String messageError = "We were unable to update your information. It is not allowed to change the CPF.";
		
		requestUserDTO.setCpf("111.350.764-05");
		when(repository.findById(any())).thenReturn(Optional.of(user));
		
		String message = assertThrows(UpdateNotAllowedException.class, () -> {
			services.update(requestUserDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
		
	}
	
	@Test
	public void reactivate() {
		user.setActive(false);
		when(repository.findById(any())).thenReturn(Optional.of(user));
		ResponseEntity<ResponseUserDTO> user = services.reactivate(id);
		assertTrue(user.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void reactivateResourceNotFoundException() {
		String messageError = "Sorry, we could not find a user with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.reactivate(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void reactivateValidationException() {
		String messageError = "This user already has an active account.";
		
		when(repository.findById(any())).thenReturn(Optional.of(user));
		
		String message = assertThrows(ValidationException.class, () -> {
			services.reactivate(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}

}
