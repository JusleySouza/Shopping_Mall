package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.FindByIdCoupon;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class FindByIdCouponImplement implements FindByIdCoupon {

	private final CouponGateway couponGateway;

	@Override
	public Coupon findById(UUID idCoupon) {
		return couponGateway.findByIdCoupon(idCoupon);
	}
	
}
