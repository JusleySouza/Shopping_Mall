package com.shopping.mall.productsapi.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.shopping.mall.productsapi.exception.DuplicateSkuException;
import com.shopping.mall.productsapi.exception.ResourceNotFoundException;
import com.shopping.mall.productsapi.exception.UpdateNotAllowedException;
import com.shopping.mall.productsapi.exception.ValidationException;
import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.Product;
import com.shopping.mall.productsapi.model.SubCategory;
import com.shopping.mall.productsapi.model.dto.RequestProductDTO;
import com.shopping.mall.productsapi.model.dto.ResponseProductDTO;
import com.shopping.mall.productsapi.repository.CategoryRepository;
import com.shopping.mall.productsapi.repository.ProductRepository;
import com.shopping.mall.productsapi.repository.SubCategoryRepository;
import com.shopping.mall.productsapi.test.utils.ClassBuilder;

class ProductServicesImplementTest {

	@InjectMocks
	private ProductServicesImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private ProductRepository repository;
	
	@Mock
	private SubCategoryRepository subCategoryRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	private Product product;
	private SubCategory subCategory;
	private Category category;
	private RequestProductDTO requestProductDTO;
	private ResponseProductDTO responseProductDTO;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;
	private List<ResponseProductDTO> listResponse;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = UUID.randomUUID();
		product = ClassBuilder.productBuilder();
		subCategory = ClassBuilder.subCategoryBuilder();
		category = ClassBuilder.categoryBuilder();
		requestProductDTO = ClassBuilder.requestProductDTOBuilder();
		responseProductDTO = ClassBuilder.responseProductDTOBuilder();
		
		product.setId(id);
		responseProductDTO.setId(id);
		product.setSubCategory(ClassBuilder.subCategoryBuilder());
		product.setCategory(ClassBuilder.categoryBuilder());
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
	}

	@Test
	public void create() {
		when(subCategoryRepository.findById(any())).thenReturn(Optional.of(subCategory));
		when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
		ResponseEntity<Object> product = services.create(requestProductDTO);
		assertTrue(product.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	
	@Test 
	public void createWithMissingFields() {
		requestProductDTO.setName(null);
		
		violations = validator.validate(requestProductDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> product = services.create(requestProductDTO);
		
		assertTrue(product.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));	
	}
	
	
	@Test
	public void createSubCategoryResourceNotFoundException() {
		String messageError = "Sorry, we could not find a sub_category with this id. Check and try again.";
		
		when(subCategoryRepository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.create(requestProductDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void createCategoryResourceNotFoundException() {
		String messageError = "Sorry, we could not find a category with this id. Check and try again.";
		
		when(subCategoryRepository.findById(any())).thenReturn(Optional.of(subCategory));
		
		when(categoryRepository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.create(requestProductDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void createWithDuplicateSku() {
		String messageError = "Could not register product. There is already a product registered with this sku. Check and try again.";
		when(subCategoryRepository.findById(any())).thenReturn(Optional.of(subCategory));
		when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
		when(repository.findBySku(anyInt())).thenReturn(product);
		
		String message = assertThrows(DuplicateSkuException.class, () -> {
			services.create(requestProductDTO);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void findAll() {
		when(repository.findAllByActiveTrue()).thenReturn(List.of(product));
		listResponse = services.findAll();
		assertNotNull(listResponse);
	}
	
	
	@Test
	public void findById() throws Exception{
		when(repository.findByIdAndActiveTrue(any())).thenReturn(product);
		ResponseProductDTO productId = services.findById(id);
		assertEquals(productId.toString(), responseProductDTO.toString());
	}
	
	
	@Test
	public void findByIdResourceNotFoundException() {
		String messageError = "Sorry, we could not find a product with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findById(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void findByName() throws Exception{
		when(repository.findByName(anyString())).thenReturn(product);
		ResponseProductDTO productName = services.findByName("televisão");
		assertEquals(productName.toString(), responseProductDTO.toString());
	}
	
	
	@Test
	public void findByNameResourceNotFoundException() {
		String messageError = "Could not find a product with this name. Check and try again.";
		
		when(repository.findByName(anyString())).thenReturn(null);
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByName("televisão");
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void findByNameValidationException() {
		String messageError = "Sorry, unable to search by name. The name entered must be between 2 and 45 characters long.";
		
		when(repository.findByName(anyString())).thenReturn(null);
		
		String message = assertThrows(ValidationException.class, () -> {
			services.findByName("P");
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(product));
		when(subCategoryRepository.findById(any())).thenReturn(Optional.of(subCategory));
		when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
		ResponseEntity<Object> product = services.update(requestProductDTO, id);
		assertTrue(product.getStatusCode().equals(HttpStatus.OK));
	}
	
	
	@Test
	public void updateWithMissingFields() {
		requestProductDTO.setName(null);
		
		violations = validator.validate(requestProductDTO);
		
		when(mockValidator.validate(any())).thenReturn(violations);
		
		ResponseEntity<Object> product = services.update(requestProductDTO, id);
		
		assertTrue(product.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "Sorry, we could not find a product with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestProductDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void updateResourceNotFoundExceptionSubCategory() {
		String messageError = "Sorry, we could not find a sub_category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.of(product));
		
		when(subCategoryRepository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestProductDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void updateResourceNotFoundExceptionCategory() {
		String messageError = "Sorry, we could not find a category with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.of(product));
		
		when(subCategoryRepository.findById(any())).thenReturn(Optional.of(subCategory));
		
		when(categoryRepository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestProductDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	
	@Test
	public void updateNotAllowedException() {
		String messageError = "We were unable to update your information. It is not allowed to change the SKU.";
		
		requestProductDTO.setSku(1234);
		when(repository.findById(any())).thenReturn(Optional.of(product));
		
		String message = assertThrows(UpdateNotAllowedException.class, () -> {
			services.update(requestProductDTO, id);
		}).getMessage();
		
		assertEquals(messageError, message);
		
	}
	
	
	@Test
	public void delete() {
		when(repository.findById(any())).thenReturn(Optional.of(product));
		Product product = services.delete(id);
		assertTrue(product.getActive().equals(false));
	}
	
	
	@Test
	public void deleteResourceNotFoundException() {
		String messageError = "Sorry, we could not find a product with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void deleteValidationException() {
		String messageError = "This product is already inactive.";
		
		product.setActive(false);
		
		when(repository.findById(any())).thenReturn(Optional.of(product));
		
		String message = assertThrows(ValidationException.class, () -> {
			services.delete(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void findByCategory() {
		when(repository.findByCategoryId(id)).thenReturn(List.of(product));
		listResponse = services.findByCategory(id);
		assertNotNull(listResponse);
	}
	
	@Test
	public void findBySubCategory() {
		when(repository.findBySubCategoryId(id)).thenReturn(List.of(product));
		listResponse = services.findBySubCategory(id);
		assertNotNull(listResponse);
	}
	
	@Test
	public void reactivate() {
		product.setActive(false);
		when(repository.findById(any())).thenReturn(Optional.of(product));
		ResponseEntity<ResponseProductDTO> user = services.reactivate(id);
		assertTrue(user.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void reactivateResourceNotFoundException() {
		String messageError = "Sorry, we could not find a product with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.reactivate(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void reactivateValidationException() {
		String messageError = "This product is already active.";
		
		when(repository.findById(any())).thenReturn(Optional.of(product));
		
		String message = assertThrows(ValidationException.class, () -> {
			services.reactivate(id);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void subtraction() {
		when(repository.findById(any())).thenReturn(Optional.of(product));
		services.subtraction(id, 3);
	}
	
	@Test
	public void subtractionResultEqualToZero() {
		when(repository.findById(any())).thenReturn(Optional.of(product));
		services.subtraction(id, 5);
	}
	
	@Test
	public void subtractionResourceNotFoundException() {
		String messageError = "Sorry, we could not find a product with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.subtraction(id, 2);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void subtractionValidationException() {
		String messageError = "Insufficient stock quantity of the product " + product.getName()
		+ ". Available quantity: " + product.getStock() + ".";
		
		product.setStock(5);
		
		when(repository.findById(any())).thenReturn(Optional.of(product));
		
		String message = assertThrows(ValidationException.class, () -> {
			services.subtraction(id, 10);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void addition() {
		when(repository.findById(any())).thenReturn(Optional.of(product));
		services.addition(id, 3);
	}
	
	@Test
	public void additionResourceNotFoundException() {
		String messageError = "Sorry, we could not find a product with this id. Check and try again.";
		
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.addition(id, 2);
		}).getMessage();
		
		assertEquals(messageError, message);
	}
	
	@Test
	public void additionValidationException() {
		String messageError = "There are no products to return to stock.";
				
		when(repository.findById(any())).thenReturn(Optional.of(product));
		
		String message = assertThrows(ValidationException.class, () -> {
			services.addition(id, 0);
		}).getMessage();
		
		assertEquals(messageError, message);
	}

}
