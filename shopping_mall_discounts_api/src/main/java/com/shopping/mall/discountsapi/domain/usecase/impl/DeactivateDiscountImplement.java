package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.DiscountGateway;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.domain.usecase.DeactivateDiscount;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class DeactivateDiscountImplement implements DeactivateDiscount {
	
	private final DiscountGateway discountGateway;

	@Override
	public Discount deactivate(UUID idDiscount) {
		return discountGateway.deactivateDiscount(idDiscount);
	}

}
