package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.UpdateCoupon;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class UpdateCouponImplement implements UpdateCoupon {
	
	private final CouponGateway couponGateway;
	
	@Override
	public Coupon update(Coupon coupon, UUID idCoupon) {
		return couponGateway.updateCoupon(coupon, idCoupon);
	}

}
