package com.shopping.mall.discountsapi.app.service.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
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

import com.shopping.mall.discountsapi.app.dto.RequestDiscountDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseDiscountDTO;
import com.shopping.mall.discountsapi.app.mapper.DiscountMapper;
import com.shopping.mall.discountsapi.app.service.impl.DiscountServiceImplement;
import com.shopping.mall.discountsapi.cross.exception.ValidationException;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.domain.usecase.ActivateDiscount;
import com.shopping.mall.discountsapi.domain.usecase.DeactivateDiscount;
import com.shopping.mall.discountsapi.domain.usecase.FindAllTypeDiscount;
import com.shopping.mall.discountsapi.domain.usecase.FindByIdDiscount;
import com.shopping.mall.discountsapi.domain.usecase.SaveDiscount;
import com.shopping.mall.discountsapi.domain.usecase.UpdateDiscount;
import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class DiscountServiceImplementTest {
	

	@InjectMocks
	private DiscountServiceImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private DiscountMapper mapper;
	
	private Discount discount;
	
	@Mock
	private SaveDiscount saveDiscount;
	
	@Mock
	private UpdateDiscount updateDiscount;
	
	@Mock
	private FindAllTypeDiscount findAllTypeDiscount;
	
	@Mock
	private FindByIdDiscount findByIdDiscount;
	
	@Mock
	private DeactivateDiscount deactivateDiscount;
	
	@Mock
	private ActivateDiscount activateDiscount;
	
	private ResponseDiscountDTO responseDiscountDTO;
	private RequestDiscountDTO requestDiscountDTO;
	private List<ResponseDiscountDTO> listResponseDiscountDTO;
	private List<Discount> listDiscounts;
	private UUID id;
	
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		discount = ClassBuilder.discountBuilder();
		requestDiscountDTO = ClassBuilder.requestDiscountDTOBuilder();
		responseDiscountDTO = ClassBuilder.responseDiscountDTOBuilder();
		id = UUID.randomUUID();
	}

	@Test
	public void save() {
		when(mapper.toModel(any())).thenReturn(discount);
		when(mapper.modelToResponseDiscountDTO(any())).thenReturn(responseDiscountDTO);
		when(saveDiscount.save(any())).thenReturn(discount);
		ResponseEntity<Object> discount1 = services.saveDiscount(requestDiscountDTO);
		assertTrue(discount1.getStatusCode().equals(HttpStatus.CREATED));
	}

	
	@Test 
	public void saveWithMissingFields() {
		requestDiscountDTO.setIdObjectDiscount(null);
		violations = validator.validate(requestDiscountDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> discount1 = services.saveDiscount(requestDiscountDTO);
		assertTrue(discount1.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@Test
	public void saveValidationException() {
		String messageError = "The date must be later than the current date.";
		requestDiscountDTO.setExpiration(LocalDate.parse("2023-08-01"));		
		String message = assertThrows(ValidationException.class, () -> {
			services.saveDiscount(requestDiscountDTO);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(mapper.toModel(any())).thenReturn(discount);
		when(mapper.modelToResponseDiscountDTO(any())).thenReturn(responseDiscountDTO);
		when(updateDiscount.update(any(), any())).thenReturn(discount);
		ResponseEntity<Object> discount1 = services.updateDiscount(requestDiscountDTO, id);
		assertTrue(discount1.getStatusCode().equals(HttpStatus.OK));
	}

	@Test 
	public void updateWithMissingFields() {
		requestDiscountDTO.setIdObjectDiscount(null);
		violations = validator.validate(requestDiscountDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> discount1 = services.updateDiscount(requestDiscountDTO, id);
		assertTrue(discount1.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@Test
	public void updateValidationException() {
		String messageError = "The date must be later than the current date.";
		requestDiscountDTO.setExpiration(LocalDate.parse("2023-08-01"));		
		String message = assertThrows(ValidationException.class, () -> {
			services.updateDiscount(requestDiscountDTO, id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void findAllTypeDiscount() {
		when(mapper.modelToResponseDiscountDTO(any())).thenReturn(responseDiscountDTO);
		when(findAllTypeDiscount.findAll(anyString())).thenReturn(listDiscounts);
		listResponseDiscountDTO = services.findAllTypeDiscount("PROD");
		assertNotNull(listResponseDiscountDTO);
	}
	
	@Test
	public void findById() {
		when(mapper.modelToResponseDiscountDTO(any())).thenReturn(responseDiscountDTO);
		when(findByIdDiscount.findById(any())).thenReturn(discount);
		responseDiscountDTO = services.findByIdDiscount(id);
		assertNotNull(responseDiscountDTO);
	}
	
	@Test
	public void deactivate() {
		when(mapper.modelToResponseDiscountDTO(any())).thenReturn(responseDiscountDTO);
		when(deactivateDiscount.deactivate(any())).thenReturn(discount);
		ResponseEntity<ResponseDiscountDTO> discount1 = services.deactivateDiscount(id);
		assertTrue(discount1.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void activate() {
		when(mapper.modelToResponseDiscountDTO(any())).thenReturn(responseDiscountDTO);
		when(activateDiscount.activate(any())).thenReturn(discount);
		ResponseEntity<ResponseDiscountDTO> discount1 = services.activateDiscount(id);
		assertTrue(discount1.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
}
