package com.shopping.mall.discountsapi.domain.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.usecase.FindAllActivatedCoupon;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
@Component
public class FindAllActivatedCouponImplement  implements FindAllActivatedCoupon{
	
	private final CouponGateway couponGateway;
	
	@Override
	public List<Coupon> findAll(){
		return couponGateway.findAllActivated();
	}

}
