package com.shopping.mall.discountsapi.domain.usecase.impl;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.DiscountGateway;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.domain.usecase.SaveDiscount;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class SaveDiscountImplement implements SaveDiscount {
	
	private final DiscountGateway discountGateway;

	@Override
	public Discount save(Discount discount) {
		return discountGateway.saveDiscount(discount);
	}

}
