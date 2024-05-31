package com.shopping.mall.discountsapi.infra.dataprovider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.shopping.mall.discountsapi.cross.exception.DuplicateDiscountException;
import com.shopping.mall.discountsapi.cross.exception.ResourceNotFoundException;
import com.shopping.mall.discountsapi.cross.exception.ValidationException;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.infra.entity.DiscountEntity;
import com.shopping.mall.discountsapi.infra.mapper.DiscountDataProviderMapper;
import com.shopping.mall.discountsapi.infra.repository.DiscountRepository;
import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

class DiscountDataProviderTest {
	
	@InjectMocks
	private DiscountDataProvider discountDataProvider;
	
	@Mock
	private DiscountDataProviderMapper mapper;
	
	@Mock
	private DiscountRepository repository;
	
	private DiscountEntity discountEntity;
	
	private DiscountEntity discountEntityCopy;
	
	private Discount discount;
		
	private List<DiscountEntity> listDiscounts;
	
	private List<Discount> listDiscountModel;
		
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		id = UUID.randomUUID();
		discount = ClassBuilder.discountBuilder();
		discountEntity = ClassBuilder.discountEntityBuilder();
		discountEntityCopy = ClassBuilder.discountEntityBuilder();
		discount.setId(id);
		discountEntity.setId(id);
		discountEntityCopy.setId(UUID.randomUUID());
		listDiscounts = new ArrayList<>();
	}

	@Test
	public void save() {
		when(repository.findByTypeDiscountAndIdObjectDiscountAndActiveTrue(anyString(), any())).thenReturn(listDiscounts);
		when(mapper.toModel(discountEntity)).thenReturn(discount);
		when(mapper.toEntity(discount)).thenReturn(discountEntity);
		when(repository.save(any())).thenReturn(discountEntity);
		Discount discount1 = discountDataProvider.saveDiscount(discount);
		assertTrue(discount.getId().equals(discount1.getId()));
	}
	
	@Test
	public void saveDuplicateDiscountException() {
		String messageError = "There is already a discount with this registration.";
		listDiscounts.add(discountEntity);
		when(repository.findByTypeDiscountAndIdObjectDiscountAndActiveTrue(anyString(), any())).thenReturn(listDiscounts);
		String message = assertThrows(DuplicateDiscountException.class, () -> {
			discountDataProvider.saveDiscount(discount);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(discountEntity));
		discountEntity.setExpiration(LocalDate.parse("2035-09-08"));
		when(repository.findByTypeDiscountAndPercentageDiscountAndExpirationAndActiveTrue(anyString(), anyDouble(), any())).thenReturn(listDiscounts);
		when(repository.save(any())).thenReturn(discountEntity);
		when(mapper.toModel(any())).thenReturn(discount);
		when(mapper.updateDiscount(any(), any())).thenReturn(discountEntity);
		Discount discount1 = discountDataProvider.updateDiscount(discount, id);
		assertTrue(discount.getId().equals(discount1.getId()));
	}
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a discount with this ID. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			discountDataProvider.updateDiscount(discount, id);
		}).getMessage();
		assertEquals(messageError, message);
	}

	@Test
	public void updateValidationException() {
		String messageError = "Expiration date must be different from current date.";
		when(repository.findById(any())).thenReturn(Optional.of(discountEntity));
		when(repository.findByTypeDiscountAndPercentageDiscountAndExpirationAndActiveTrue(anyString(), anyDouble(), any())).thenReturn(listDiscounts);
		String message = assertThrows(ValidationException.class, () -> {
			discountDataProvider.updateDiscount(discount, id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void updateDuplicateDiscountException() {
		String messageError = "You already have a discount registered with these attributes. "
				+ "Change the discount type, percentage and expiration date of any of them and try again.";
		when(repository.findById(any())).thenReturn(Optional.of(discountEntity));
		listDiscounts.add(discountEntity);
		listDiscounts.add(discountEntityCopy);
		when(repository.findByTypeDiscountAndPercentageDiscountAndExpirationAndActiveTrue(anyString(), anyDouble(), any())).thenReturn(listDiscounts);
		String message = assertThrows(DuplicateDiscountException.class, () -> {
			discountDataProvider.updateDiscount(discount, id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void findAllTypesDiscount() {
		when(repository.findAllByTypeDiscount(anyString())).thenReturn(List.of(discountEntity));
		listDiscountModel = discountDataProvider.findAllTypeDiscount(anyString());
		assertNotNull(listDiscountModel);
	}
	
	@Test
	public void findById() {
		when(repository.findById(any())).thenReturn(Optional.of(discountEntity));
		when(mapper.toModel(any())).thenReturn(discount);
		Discount discount1 = discountDataProvider.findByIdDiscount(id);
		assertEquals(discount.getId(), discount1.getId());
	}
	
	@Test
	public void findByIdResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a discount with this ID. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			discountDataProvider.findByIdDiscount(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void deactivate() {
		when(repository.findById(any())).thenReturn(Optional.of(discountEntity));
		when(mapper.toModel(any())).thenReturn(discount);
		when(mapper.discountDeactivate(any())).thenReturn(discountEntity);
		when(repository.save(any())).thenReturn(discountEntity);
		Discount discount1 = discountDataProvider.deactivateDiscount(id);
		assertTrue(discount.getActive().equals(discount1.getActive()));
	}
	
	@Test
	public void deactivateResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a discount with this ID. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			discountDataProvider.deactivateDiscount(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void deactivateValidationException() {
		String messageError = "This discount is already deactivated.";
		when(repository.findById(any())).thenReturn(Optional.of(discountEntity));
		discountEntity.setActive(Boolean.FALSE);
		String message = assertThrows(ValidationException.class, () -> {
			discountDataProvider.deactivateDiscount(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void activate() {
		discountEntity.setActive(Boolean.FALSE);
		when(repository.findById(any())).thenReturn(Optional.of(discountEntity));
		when(mapper.toModel(any())).thenReturn(discount);
		when(mapper.discountActivate(any())).thenReturn(discountEntity);
		when(repository.save(any())).thenReturn(discountEntity);
		Discount discount1 = discountDataProvider.activateDiscount(id);
		assertTrue(discount.getActive().equals(discount1.getActive()));
	}
	
	@Test
	public void activateResourceNotFoundException() {
		String messageError = "Sorry, we were unable to find a dicount with this ID. Check and try again.";
		when(repository.findById(any())).thenReturn(Optional.empty());
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			discountDataProvider.activateDiscount(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void activateValidationException() {
		String messageError = "This discount is already activated.";
		when(repository.findById(any())).thenReturn(Optional.of(discountEntity));
		discountEntity.setActive(Boolean.TRUE);
		String message = assertThrows(ValidationException.class, () -> {
			discountDataProvider.activateDiscount(id);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
}
