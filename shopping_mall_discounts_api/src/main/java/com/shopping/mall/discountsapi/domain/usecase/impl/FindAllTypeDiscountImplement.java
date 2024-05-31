package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.DiscountGateway;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.domain.usecase.FindAllTypeDiscount;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class FindAllTypeDiscountImplement implements FindAllTypeDiscount {
	
	private final DiscountGateway discountGateway;

	@Override
	public List<Discount> findAll(String typeDiscount) {
		return discountGateway.findAllTypeDiscount(typeDiscount);
	}

}
