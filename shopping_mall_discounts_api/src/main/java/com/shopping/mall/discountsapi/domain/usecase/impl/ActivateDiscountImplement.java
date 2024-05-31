package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.DiscountGateway;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.domain.usecase.ActivateDiscount;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class ActivateDiscountImplement implements ActivateDiscount {
	
	private final DiscountGateway discountGateway;

	@Override
	public Discount activate(UUID idDiscount) {
		return discountGateway.activateDiscount(idDiscount);
	}

}
