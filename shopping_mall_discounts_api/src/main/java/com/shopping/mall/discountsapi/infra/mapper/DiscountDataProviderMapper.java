package com.shopping.mall.discountsapi.infra.mapper;

import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.infra.entity.DiscountEntity;

public interface DiscountDataProviderMapper {
	
	public Discount toModel(DiscountEntity discountEntity);
	
	public DiscountEntity toEntity(Discount discount);
	
	public DiscountEntity updateDiscount(Discount discount, DiscountEntity discounEntity);
	
	public DiscountEntity discountDeactivate(DiscountEntity discountEntity);
	
	public DiscountEntity discountActivate(DiscountEntity discountEntity);
	
}
