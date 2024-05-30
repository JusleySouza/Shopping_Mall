package com.shopping.mall.discountsapi.infra.mapper;

import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.infra.entity.CouponEntity;

public interface CouponDataProviderMapper {
	
	public Coupon toModel(CouponEntity couponEntity);
	
	public CouponEntity toEntity(Coupon coupon);
	
	public CouponEntity updateCoupon(Coupon coupon, CouponEntity couponEntity) ;
	
	public CouponEntity couponDeactivate(CouponEntity couponEntity);
	
	public CouponEntity couponActivate(CouponEntity couponEntity);

}
