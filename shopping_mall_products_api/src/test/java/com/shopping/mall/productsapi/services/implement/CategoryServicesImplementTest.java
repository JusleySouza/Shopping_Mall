package com.shopping.mall.productsapi.services.implement;

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

import com.shopping.mall.productsapi.exception.DuplicateCategoryException;
import com.shopping.mall.productsapi.exception.ResourceNotFoundException;
import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.dto.RequestCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseCategoryDTO;
import com.shopping.mall.productsapi.repository.CategoryRepository;
import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class CategoryServicesImplementTest {

	@InjectMocks
	private CategoryServicesImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private CategoryRepository repository;
	
	private Category category;
	private RequestCategoryDTO requestCategoryDTO;
	private ResponseCategoryDTO responseCategoryDTO;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;
	private List<ResponseCategoryDTO> listResponse;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = UUID.randomUUID();
		category = ClassBuilder.categoryBuilder();
		requestCategoryDTO = ClassBuilder.requestCategoryDTOBuilder();
		responseCategoryDTO = ClassBuilder.responseCategoryDTOBuilder();
		
		category.setId(id);
		responseCategoryDTO.setId(id);
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
	}

	@Test
	public void create() {
		ResponseEntity<Object> category = services.create(requestCategoryDTO);
		assertTrue(category.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	
	@Test 
	public void createWithMissingFields() {
		requestCategoryDTO.setNameCategory(null);
		
		violations = validator.validate(requestCategoryDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> category = services.create(requestCategoryDTO);
		
		assertTrue(category.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));	
	}
	
	
	@Test
	public void createWithDuplicateCategory() {
		String messageError = "Could not register category. There is already a category registered with this name. Check and try again.";
		when(repository.findByNameCategory(anyString())).thenReturn(category);
		
		String message = assertThrows(DuplicateCategoryException.class, () -> {
			services.create(requestCategoryDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void findAll() {
		when(repository.findAll()).thenReturn(List.of(category));
		listResponse = services.findAll();
		assertNotNull(listResponse);
	}
	
	
	@Test
	public void findById() throws Exception{
		when(repository.findById(any())).thenReturn(Optional.of(category));
		ResponseCategoryDTO categoryId = services.findById(id);
		assertEquals(categoryId.toString(), responseCategoryDTO.toString());
	}
	
	
	@Test
	public void findByIdResourceNotFoundException() {
		String messageError = "Sorry, we could not find a category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findById(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(category));
		ResponseEntity<Object> category = services.update(requestCategoryDTO, id);
		assertTrue(category.getStatusCode().equals(HttpStatus.OK));
	}
	
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "Sorry, we could not find a category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestCategoryDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void updateWithMissingFields() {
		requestCategoryDTO.setNameCategory(null);
		
		violations = validator.validate(requestCategoryDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> user = services.update(requestCategoryDTO, id);
		
		assertTrue(user.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	
	@Test
	public void delete() {
		when(repository.findById(any())).thenReturn(Optional.of(category));
		ResponseEntity<Object> category = services.delete(id);
		assertTrue(category.getStatusCode().equals(HttpStatus.OK));
	}
	
	
	@Test
	public void deleteResourceNotFoundException() {
		String messageError = "Sorry, we could not find a category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}

}
