package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.DeactivateCoupon;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class DeactivateCouponImplement implements DeactivateCoupon {
	
	private final CouponGateway couponGateway;

	@Override
	public Coupon deactivateCoupon(UUID idCoupon) {
		return couponGateway.deactivateCoupon(idCoupon);
	}

}
