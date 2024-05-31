package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.FindAllDeactivatedCoupon;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class FindAllDeactivedCouponImplement  implements FindAllDeactivatedCoupon{
	
	private final CouponGateway couponGateway;
	
	@Override
	public List<Coupon> findAllDeactivated(){
		return couponGateway.findAllDeactivated();
	}

}
