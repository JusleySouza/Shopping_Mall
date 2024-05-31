package com.shopping.mall.discountsapi.domain.usecase;

import java.util.List;

import com.shopping.mall.discountsapi.domain.model.Coupon;

public interface FindAllActivatedCoupon {
	
	List<Coupon> findAll();

}
