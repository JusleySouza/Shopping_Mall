package com.shopping.mall.discountsapi.domain.usecase;

import java.util.UUID;

import com.shopping.mall.discountsapi.domain.model.Coupon;

public interface FindByIdCoupon {
	
	Coupon findById(UUID idCoupon);

}
