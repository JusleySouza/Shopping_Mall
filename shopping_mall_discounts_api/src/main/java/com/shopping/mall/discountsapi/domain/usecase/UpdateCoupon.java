package com.shopping.mall.discountsapi.domain.usecase;

import java.util.UUID;

import com.shopping.mall.discountsapi.domain.model.Coupon;

public interface UpdateCoupon {
	
	Coupon update(Coupon coupon, UUID idCoupon);

}
