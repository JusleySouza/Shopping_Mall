package com.shopping.mall.discountsapi.domain.usecase;

import com.shopping.mall.discountsapi.domain.model.Coupon;

public interface FindByCodeCoupon {
	
	Coupon findByCode(String codeCoupon);

}
