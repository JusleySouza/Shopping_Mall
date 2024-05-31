package com.shopping.mall.discountsapi.domain.usecase;

import com.shopping.mall.discountsapi.domain.model.Coupon;

public interface SaveCoupon {
	
	Coupon save(Coupon coupon);

}
