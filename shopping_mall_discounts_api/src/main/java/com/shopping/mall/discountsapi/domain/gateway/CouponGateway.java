package com.shopping.mall.discountsapi.domain.gateway;

import java.util.List;
import java.util.UUID;

import com.shopping.mall.discountsapi.domain.model.Coupon;

public interface CouponGateway {
	
	Coupon saveCoupon(Coupon coupon);
	
	Coupon updateCoupon(Coupon coupon, UUID idCoupon);
	
	Coupon deactivateCoupon(UUID idCoupon);
	
	Coupon activateCoupon(UUID idCoupon);
	
	Coupon findByIdCoupon(UUID idCoupon);
	
	Coupon findByCodeCoupon(String codeCoupon);
	
	List<Coupon> findAllActivated();
	
	List<Coupon> findAllDeactivated();

}
