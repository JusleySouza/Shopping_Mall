package com.shopping.mall.discountsapi.infra.mapper.impl;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.infra.entity.DiscountEntity;
import com.shopping.mall.discountsapi.infra.mapper.DiscountDataProviderMapper;

import lombok.Generated;

@Generated
@Component
public class DiscountDataProviderMapperImpl implements DiscountDataProviderMapper {

	@Override
	public Discount toModel(DiscountEntity discountEntity) {
		return Discount.builder()
				.id(discountEntity.getId())
				.typeDiscount(discountEntity.getTypeDiscount())
				.idObjectDiscount(discountEntity.getIdObjectDiscount())
				.percentageDiscount(discountEntity.getPercentageDiscount())
				.expiration(discountEntity.getExpiration())
				.created(discountEntity.getCreated())
				.active(discountEntity.getActive())
				.build();
	}

	@Override
	public DiscountEntity toEntity(Discount discount) {
		return DiscountEntity.builder()
				.id(discount.getId())
				.typeDiscount(discount.getTypeDiscount())
				.idObjectDiscount(discount.getIdObjectDiscount())
				.percentageDiscount(discount.getPercentageDiscount())
				.expiration(discount.getExpiration())
				.created(discount.getCreated())
				.active(discount.getActive())
				.build();
	}
	
	@Override
	public DiscountEntity updateDiscount(Discount discount, DiscountEntity discounEntity) {
		discounEntity.setTypeDiscount(discount.getTypeDiscount().toString());
		discounEntity.setIdObjectDiscount(discount.getIdObjectDiscount());
		discounEntity.setPercentageDiscount(discount.getPercentageDiscount());
		discounEntity.setExpiration(discount.getExpiration());
		return discounEntity;
	}

	@Override
	public DiscountEntity discountDeactivate(DiscountEntity discountEntity) {
		discountEntity.setActive(Boolean.FALSE);
		return discountEntity;
	}

	@Override
	public DiscountEntity discountActivate(DiscountEntity discountEntity) {
		discountEntity.setActive(Boolean.TRUE);
		return discountEntity;
	}

}
