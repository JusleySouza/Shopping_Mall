package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.DiscountGateway;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.domain.usecase.UpdateDiscount;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class UpdateDiscountImplement implements UpdateDiscount {
	
	private final DiscountGateway discountGateway;

	@Override
	public Discount update(Discount discount, UUID idDiscount) {
		return discountGateway.updateDiscount(discount, idDiscount);
	}

}
