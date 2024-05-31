package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.ActivateCoupon;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class ActivateCouponImplement implements ActivateCoupon {
	
	private final CouponGateway couponGateway;

	@Override
	public Coupon activate(UUID idCoupon) {
		return couponGateway.activateCoupon(idCoupon);
	}

}
