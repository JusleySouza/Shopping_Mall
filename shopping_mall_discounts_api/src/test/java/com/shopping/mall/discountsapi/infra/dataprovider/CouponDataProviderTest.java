package com.shopping.mall.discountsapi.infra.dataprovider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.shopping.mall.discountsapi.cross.exception.ResourceNotFoundException;
import com.shopping.mall.discountsapi.cross.exception.ValidationException;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.infra.entity.CouponEntity;
import com.shopping.mall.discountsapi.infra.mapper.CouponDataProviderMapper;
import com.shopping.mall.discountsapi.infra.repository.CouponRepository;
import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class CouponDataProviderTest {
	
	@InjectMocks
	private CouponDataProvider couponDataProvider;
	
	@Mock
	private CouponDataProviderMapper mapper;
	
	@Mock
	private CouponRepository repository;
	
	private CouponEntity couponEntity;
	
	private CouponEntity couponEntityCopy;
	
	private Coupon coupon;
		
	private List<Coupon> listCoupons;
		
	private UUID id;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = UUID.randomUUID();
		coupon = ClassBuilder.couponBuilder();
		couponEntity = ClassBuilder.couponEntityBuilder();
		couponEntityCopy = ClassBuilder.couponEntityBuilder();
		coupon.setId(id);
		couponEntity.setId(id);
		couponEntityCopy.setId(UUID.randomUUID());
	}

	@Test
	public void save() {
		when(mapper.toModel(any())).thenReturn(coupon);
		when(mapper.toEntity(any())).thenReturn(couponEntity);
		when(repository.save(any())).thenReturn(couponEntity);
		Coupon coupon1 = couponDataProvider.saveCoupon(coupon);
		assertTrue(coupon.getCode().equals(coupon1.getCode()));
	}
	
	@Test
	public void saveValidationException() {
		String messageError = "A coupon with this code already exists.";
		when(repository.findByCode(anyString())).thenReturn(couponEntity);
		String message = assertThrows(ValidationException.class, () -> {
			couponDataProvider.saveCoupon(coupon);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(couponEntity));
		when(repository.findByCode(anyString())).thenReturn(couponEntity);
		when(repository.save(any())).thenReturn(couponEntity);
		when(mapper.toModel(any())).thenReturn(coupon);
		when(mapper.updateCoupon(any(), any())).thenReturn(couponEntity);
		Coupon coupon1 = couponDataProvider.updateCoupon(coupon, id);
		assertTrue(coupon.getCode().equals(coupon1.getCode()));
	}
	
	@Test
	public void updateValidationException() {
		String messageError = "You already have a coupon registered with this code. Change the code of any of them and try again.";
		when(repository.findById(any())).thenReturn(Optional.of(couponEntity));
		when(repository.findByCode(any())).thenReturn(couponEntityCopy);
		String message = assertThrows(ValidationException.class, () -> {
			couponDataProvider.updateCoupon(coupon, id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a coupon with this ID. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			couponDataProvider.updateCoupon(coupon, id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void deactivate() {
		when(repository.findById(any())).thenReturn(Optional.of(couponEntity));
		when(mapper.toModel(any())).thenReturn(coupon);
		when(mapper.couponDeactivate(any())).thenReturn(couponEntity);
		when(repository.save(any())).thenReturn(couponEntity);
		Coupon coupon1 = couponDataProvider.deactivateCoupon(id);
		assertTrue(coupon.getActive().equals(coupon1.getActive()));
	}
	
	@Test
	public void deactivateResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a coupon with this ID. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			couponDataProvider.deactivateCoupon(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void deactivateValidationException() {
		String messageError = "This coupon is already deactivated.";
		when(repository.findById(any())).thenReturn(Optional.of(couponEntity));
		couponEntity.setActive(Boolean.FALSE);
		String message = assertThrows(ValidationException.class, () -> {
			couponDataProvider.deactivateCoupon(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void activate() {
		couponEntity.setActive(Boolean.FALSE);
		when(repository.findById(any())).thenReturn(Optional.of(couponEntity));
		when(mapper.toModel(any())).thenReturn(coupon);
		when(mapper.couponActivate(any())).thenReturn(couponEntity);
		when(repository.save(any())).thenReturn(couponEntity);
		Coupon coupon1 = couponDataProvider.activateCoupon(id);
		assertTrue(coupon.getActive().equals(coupon1.getActive()));
	}
	
	@Test
	public void activateResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a coupon with this ID. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			couponDataProvider.activateCoupon(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void activateValidationException() {
		String messageError = "This coupon is already activated.";
		when(repository.findById(any())).thenReturn(Optional.of(couponEntity));
		couponEntity.setActive(Boolean.TRUE);
		String message = assertThrows(ValidationException.class, () -> {
			couponDataProvider.activateCoupon(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void findById() {
		when(repository.findByIdAndActiveTrue(any())).thenReturn(couponEntity);
		when(mapper.toModel(any())).thenReturn(coupon);
		Coupon coupon1 = couponDataProvider.findByIdCoupon(id);
		assertEquals(coupon.getId(), coupon1.getId());
	}
	
	@Test
	public void findByIdResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a coupon with this ID. Check and try again.";
		when(repository.findByIdAndActiveTrue(any())).thenReturn(null);
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			couponDataProvider.findByIdCoupon(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void findAllActivated() {
		when(repository.findAllByActiveTrue()).thenReturn(List.of(couponEntity));
		listCoupons = couponDataProvider.findAllActivated();
		assertNotNull(listCoupons);
	}
	
	@Test
	public void findAllDeactivated() {
		when(repository.findAllByActiveFalse()).thenReturn(List.of(couponEntity));
		listCoupons = couponDataProvider.findAllDeactivated();
		assertNotNull(listCoupons);
	}
	
	@Test
	public void findByCode() {
		when(repository.findByCode(anyString())).thenReturn(couponEntity);
		when(mapper.toModel(any())).thenReturn(coupon);
		Coupon coupon1 = couponDataProvider.findByCodeCoupon("PROD");
		assertEquals(coupon.getCode(), coupon1.getCode());
	}
	
	@Test
	public void findByCodeResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a coupon with this code. Check and try again.";
		when(repository.findByCode(anyString())).thenReturn(null);
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			couponDataProvider.findByCodeCoupon("PROD");
		}).getMessage();
		assertEquals(messageError, message);
	}

}
