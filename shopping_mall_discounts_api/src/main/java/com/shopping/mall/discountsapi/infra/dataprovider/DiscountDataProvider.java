package com.shopping.mall.discountsapi.infra.dataprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.cross.config.LoggerConfig;
import com.shopping.mall.discountsapi.cross.exception.DuplicateDiscountException;
import com.shopping.mall.discountsapi.cross.exception.ResourceNotFoundException;
import com.shopping.mall.discountsapi.cross.exception.ValidationException;
import com.shopping.mall.discountsapi.domain.gateway.DiscountGateway;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.infra.entity.DiscountEntity;
import com.shopping.mall.discountsapi.infra.mapper.DiscountDataProviderMapper;
import com.shopping.mall.discountsapi.infra.repository.DiscountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DiscountDataProvider implements DiscountGateway {

    private final DiscountRepository repository;
    private final DiscountDataProviderMapper mapper;
    private DiscountEntity discountEntity;
    List<DiscountEntity> listDiscounts;
    List<Discount> listModel;
    
	@Override
	public Discount saveDiscount(Discount discount) {
		
		listDiscounts = repository.findByTypeDiscountAndIdObjectDiscountAndActiveTrue(discount.getTypeDiscount(), discount.getIdObjectDiscount());
		
		if(!listDiscounts.isEmpty()) {
			LoggerConfig.LOGGER_DISCOUNT.error("Duplicate discount");
			throw new DuplicateDiscountException("There is already a discount with this registration.");
		}
		
		 return mapper.toModel(repository.save(mapper.toEntity(discount)));
	}

	
	@Override
	public Discount updateDiscount(Discount discount, UUID idDiscount) {
		
		discountEntity = repository.findById(idDiscount).orElseThrow(() -> new ResourceNotFoundException(
				"Sorry, we were unable to find a discount with this ID. Check and try again."));
		
		listDiscounts = repository.findByTypeDiscountAndPercentageDiscountAndExpirationAndActiveTrue(
				discount.getTypeDiscount(), discount.getPercentageDiscount(), discount.getExpiration());
		
		if(!listDiscounts.isEmpty()) {
			for(DiscountEntity discounts : listDiscounts) {
				if(!discounts.getId().equals(discountEntity.getId())) {
					LoggerConfig.LOGGER_DISCOUNT.error("Duplicate discount");
					throw new DuplicateDiscountException("You already have a discount registered with these attributes. "
							+ "Change the discount type, percentage and expiration date of any of them and try again.");
				}
			}
		}
		
		if (discountEntity.getExpiration().equals(discount.getExpiration())) {
			throw new ValidationException(
					"Expiration date must be different from current date.");
		}
		
		return mapper.toModel(repository.save(mapper.updateDiscount(discount, discountEntity)));
	}


	@Override
	public List<Discount> findAllTypeDiscount(String typeDiscount) {
		listModel = new ArrayList<>();
		listDiscounts = repository.findAllByTypeDiscount(typeDiscount);
		
		for (DiscountEntity discount : listDiscounts) {
			listModel.add(mapper.toModel(discount));
		}
		
		return listModel;
	}


	@Override
	public Discount findByIdDiscount(UUID idDiscount) {
		discountEntity = repository.findById(idDiscount).orElseThrow(() -> new ResourceNotFoundException(
				"Sorry, we were unable to find a discount with this ID. Check and try again."));
		
		return mapper.toModel(discountEntity);
	}


	@Override
	public Discount deactivateDiscount(UUID idDiscount) {
		
		discountEntity = repository.findById(idDiscount).orElseThrow(() -> new ResourceNotFoundException(
				"Sorry, we were unable to find a discount with this ID. Check and try again."));
		
		if(discountEntity.getActive() == Boolean.FALSE) {
			throw new ValidationException("This discount is already deactivated.");
		}
		
		return mapper.toModel(repository.save(mapper.discountDeactivate(discountEntity)));
	}


	@Override
	public Discount activateDiscount(UUID idDiscount) {
		
		discountEntity = repository.findById(idDiscount).orElseThrow(() -> new ResourceNotFoundException(
				"Sorry, we were unable to find a dicount with this ID. Check and try again."));
		
		if(discountEntity.getActive() == Boolean.TRUE) {
			throw new ValidationException("This discount is already activated.");
		}
		
		return mapper.toModel(repository.save(mapper.discountActivate(discountEntity)));
	}

}
