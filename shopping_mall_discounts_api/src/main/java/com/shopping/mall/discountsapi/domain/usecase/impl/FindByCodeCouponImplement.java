package com.shopping.mall.discountsapi.domain.usecase.impl;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.FindByCodeCoupon;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class FindByCodeCouponImplement implements FindByCodeCoupon {

	private final CouponGateway couponGateway;

	@Override
	public Coupon findByCode(String codeCoupon) {
		return couponGateway.findByCodeCoupon(codeCoupon);
	}
	
}
