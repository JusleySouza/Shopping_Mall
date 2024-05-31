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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.shopping.mall.discountsapi.app.dto.RequestCouponDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseCouponDTO;
import com.shopping.mall.discountsapi.app.mapper.CouponMapper;
import com.shopping.mall.discountsapi.app.service.impl.CouponServiceImplement;
import com.shopping.mall.discountsapi.cross.exception.ValidationException;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.ActivateCoupon;
import com.shopping.mall.discountsapi.domain.usecase.DeactivateCoupon;
import com.shopping.mall.discountsapi.domain.usecase.FindAllActivatedCoupon;
import com.shopping.mall.discountsapi.domain.usecase.FindAllDeactivatedCoupon;
import com.shopping.mall.discountsapi.domain.usecase.FindByCodeCoupon;
import com.shopping.mall.discountsapi.domain.usecase.FindByIdCoupon;
import com.shopping.mall.discountsapi.domain.usecase.SaveCoupon;
import com.shopping.mall.discountsapi.domain.usecase.UpdateCoupon;
import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

class CouponServiceImplementTest {
	
	@InjectMocks
	private CouponServiceImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private CouponMapper mapper;
	
	private Coupon coupon;
	
	@Mock
	private  SaveCoupon saveCoupon;
	
	@Mock
	private  UpdateCoupon updateCoupon;
	
	@Mock
	private  DeactivateCoupon deactivateCoupon;
	
	@Mock
	private  ActivateCoupon activateCoupon;
	
	@Mock
	private  FindByIdCoupon findByIdCoupon;
	
	@Mock
	private  FindAllActivatedCoupon findAllActivatedCoupon;
	
	@Mock
	private  FindAllDeactivatedCoupon findAllDeactivatedCoupon;
	
	@Mock
	private  FindByCodeCoupon findByCodeCoupon;
	
	private ResponseCouponDTO responseCouponDTO;
	private RequestCouponDTO requestCouponDTO;
	private List<ResponseCouponDTO> listResponseCouponDTO;
	private List<Coupon> listCoupons;
	private UUID id;
	
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		coupon = ClassBuilder.couponBuilder();
		requestCouponDTO = ClassBuilder.requestCouponDTOBuilder();
		responseCouponDTO = ClassBuilder.responseCouponDTOBuilder();
		id = UUID.randomUUID();
	}

	@Test
	public void save() {
		when(mapper.toModel(any())).thenReturn(coupon);
		when(mapper.modelToResponseCouponDTO(any())).thenReturn(responseCouponDTO);
		when(saveCoupon.save(any())).thenReturn(coupon);
		ResponseEntity<Object> coupon1 = services.saveCoupon(requestCouponDTO);
		assertTrue(coupon1.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test 
	public void saveWithMissingFields() {
		requestCouponDTO.setCode(null);
		violations = validator.validate(requestCouponDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> coupon1 = services.saveCoupon(requestCouponDTO);
		assertTrue(coupon1.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@Test
	public void saveValidationException() {
		String messageError = "The date must be later than the current date.";
		requestCouponDTO.setExpiration(LocalDate.parse("2023-08-01"));		
		String message = assertThrows(ValidationException.class, () -> {
			services.saveCoupon(requestCouponDTO);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(mapper.toModel(any())).thenReturn(coupon);
		when(mapper.modelToResponseCouponDTO(any())).thenReturn(responseCouponDTO);
		when(updateCoupon.update(any(), any())).thenReturn(coupon);
		ResponseEntity<Object> coupon1 = services.updateCoupon(requestCouponDTO, id);
		assertTrue(coupon1.getStatusCode().equals(HttpStatus.OK));
	}

	@Test 
	public void updateWithMissingFields() {
		requestCouponDTO.setCode(null);
		violations = validator.validate(requestCouponDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> coupon1 = services.updateCoupon(requestCouponDTO, id);
		assertTrue(coupon1.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@Test
	public void updateValidationException() {
		String messageError = "The date must be later than the current date.";
		requestCouponDTO.setExpiration(LocalDate.parse("2023-08-01"));		
		String message = assertThrows(ValidationException.class, () -> {
			services.updateCoupon(requestCouponDTO, id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void deactivate() {
		when(mapper.modelToResponseCouponDTO(any())).thenReturn(responseCouponDTO);
		when(deactivateCoupon.deactivateCoupon(any())).thenReturn(coupon);
		ResponseEntity<ResponseCouponDTO> coupon1 = services.deactivateCoupon(id);
		assertTrue(coupon1.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void activate() {
		when(mapper.modelToResponseCouponDTO(any())).thenReturn(responseCouponDTO);
		when(activateCoupon.activate(any())).thenReturn(coupon);
		ResponseEntity<ResponseCouponDTO> coupon1 = services.activateCoupon(id);
		assertTrue(coupon1.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void findById() {
		when(mapper.modelToResponseCouponDTO(any())).thenReturn(responseCouponDTO);
		when(findByIdCoupon.findById(any())).thenReturn(coupon);
		ResponseCouponDTO coupon1 = services.findByIdCoupon(id);
		assertNotNull(coupon1);
	}
	
	@Test
	public void findAllActivated() {
		when(mapper.modelToResponseCouponDTO(any())).thenReturn(responseCouponDTO);
		when(findAllActivatedCoupon.findAll()).thenReturn(listCoupons);
		listResponseCouponDTO = services.findAllActivated();
		assertNotNull(listResponseCouponDTO);
	}
	
	@Test
	public void findAllDeactivated() {
		when(mapper.modelToResponseCouponDTO(any())).thenReturn(responseCouponDTO);
		when(findAllDeactivatedCoupon.findAllDeactivated()).thenReturn(listCoupons);
		listResponseCouponDTO = services.findAllDeactivated();
		assertNotNull(listResponseCouponDTO);
	}
	
	@Test
	public void findByCode() {
		when(mapper.modelToResponseCouponDTO(any())).thenReturn(responseCouponDTO);
		when(findByCodeCoupon.findByCode(anyString())).thenReturn(coupon);
		responseCouponDTO = services.findByCodeCoupon("PROD");
		assertNotNull(responseCouponDTO);
	}
	
}
