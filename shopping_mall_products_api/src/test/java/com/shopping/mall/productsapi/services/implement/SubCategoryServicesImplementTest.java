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
import com.shopping.mall.productsapi.model.SubCategory;
import com.shopping.mall.productsapi.model.dto.RequestSubCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSubCategoryDTO;
import com.shopping.mall.productsapi.repository.CategoryRepository;
import com.shopping.mall.productsapi.repository.SubCategoryRepository;
import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class SubCategoryServicesImplementTest {
	
	@InjectMocks
	private SubCategoryServicesImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private SubCategoryRepository repository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	private SubCategory subCategory;
	private Category category;
	private RequestSubCategoryDTO requestSubCategoryDTO;
	private ResponseSubCategoryDTO responseSubCategoryDTO;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;
	private List<ResponseSubCategoryDTO> listResponse;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = UUID.randomUUID();
		category = ClassBuilder.categoryBuilder();
		subCategory = ClassBuilder.subCategoryBuilder();
		requestSubCategoryDTO = ClassBuilder.requestSubCategoryDTOBuilder();
		responseSubCategoryDTO = ClassBuilder.responseSubCategoryDTOBuilder();
		
		subCategory.setId(id);
		responseSubCategoryDTO.setId(id);
		subCategory.setCategory(ClassBuilder.categoryBuilder());
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
	}

	@Test
	public void create() {
		when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
		ResponseEntity<Object> subCategory = services.create(requestSubCategoryDTO);
		assertTrue(subCategory.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	
	@Test 
	public void createWithMissingFields() {
		requestSubCategoryDTO.setNameSubCategory(null);
		
		violations = validator.validate(requestSubCategoryDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> product = services.create(requestSubCategoryDTO);
		
		assertTrue(product.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));	
	}
	
	
	@Test
	public void createResourceNotFoundException() {
		String messageError = "Sorry, we could not find a category with this id. Check and try again.";
		
		when(categoryRepository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.create(requestSubCategoryDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void createWithDuplicateSubCategory() {
		String messageError = "Could not register sub_category. There is already a sub_category registered with this name. Check and try again.";
		when(repository.findByNameSubCategory(anyString())).thenReturn(subCategory);
		when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
		
		String message = assertThrows(DuplicateCategoryException.class, () -> {
			services.create(requestSubCategoryDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void findAll() {
		when(repository.findAll()).thenReturn(List.of(subCategory));
		listResponse = services.findAll();
		assertNotNull(listResponse);
	}
	
	
	@Test
	public void findById() throws Exception{
		when(repository.findById(any())).thenReturn(Optional.of(subCategory));
		ResponseSubCategoryDTO subCategoryId = services.findById(id);
		assertEquals(subCategoryId.toString(), responseSubCategoryDTO.toString());
	}
	
	
	@Test
	public void findByIdResourceNotFoundException() {
		String messageError = "Sorry, we could not find a sub_category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findById(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(subCategory));
		when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
		ResponseEntity<Object> subCategory = services.update(requestSubCategoryDTO, id);
		assertTrue(subCategory.getStatusCode().equals(HttpStatus.OK));
	}
	
	
	@Test
	public void updateWithMissingFields() {
		requestSubCategoryDTO.setNameSubCategory(null);
		
		violations = validator.validate(requestSubCategoryDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> subCategory = services.update(requestSubCategoryDTO, id);
		
		assertTrue(subCategory.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "Sorry, we could not find a sub_category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestSubCategoryDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void updateResourceNotFoundExceptionCategory() {
		String messageError = "Sorry, we could not find a category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.of(subCategory));
		
		when(categoryRepository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestSubCategoryDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void delete() {
		when(repository.findById(any())).thenReturn(Optional.of(subCategory));
		ResponseEntity<Object> subCategory = services.delete(id);
		assertTrue(subCategory.getStatusCode().equals(HttpStatus.OK));
	}
	
	
	@Test
	public void deleteResourceNotFoundException() {
		String messageError = "Sorry, we could not find a sub_category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void findByCategory() {
		when(repository.findByCategoryId(id)).thenReturn(List.of(subCategory));
		listResponse = services.findByCategory(id);
		assertNotNull(listResponse);
	}

}
