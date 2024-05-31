package com.shopping.mall.discountsapi.domain.usecase.impl;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.SaveCoupon;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class SaveCouponImplement implements SaveCoupon {
	
	private final CouponGateway couponGateway;

	@Override
	public Coupon save(Coupon coupon) {
		return couponGateway.saveCoupon(coupon);
	}

}
