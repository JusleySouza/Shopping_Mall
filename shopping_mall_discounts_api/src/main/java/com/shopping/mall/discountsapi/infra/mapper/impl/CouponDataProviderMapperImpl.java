package com.shopping.mall.discountsapi.infra.mapper.impl;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.infra.entity.CouponEntity;
import com.shopping.mall.discountsapi.infra.mapper.CouponDataProviderMapper;

import lombok.Generated;

@Generated
@Component
public class CouponDataProviderMapperImpl implements CouponDataProviderMapper {

	@Override
	public Coupon toModel(CouponEntity couponEntity) {
		return Coupon.builder()
				.id(couponEntity.getId())
				.code(couponEntity.getCode())
				.percentage(couponEntity.getPercentage())
				.minQuantityProducts(couponEntity.getMinQuantityProducts())
				.minOrderValue(couponEntity.getMinOrderValue())
				.maxDiscount(couponEntity.getMaxDiscount())
				.expiration(couponEntity.getExpiration())
				.created(couponEntity.getCreated())
				.active(couponEntity.getActive())
				.build();
	}

	@Override
	public CouponEntity toEntity(Coupon coupon) {
		return CouponEntity.builder()
				.id(coupon.getId())
				.code(coupon.getCode())
				.percentage(coupon.getPercentage())
				.minQuantityProducts(coupon.getMinQuantityProducts())
				.minOrderValue(coupon.getMinOrderValue())
				.maxDiscount(coupon.getMaxDiscount())
				.expiration(coupon.getExpiration())
				.created(coupon.getCreated())
				.active(coupon.getActive())
				.build();
	}
	
	@Override
	public CouponEntity updateCoupon(Coupon coupon, CouponEntity couponEntity) {
		couponEntity.setCode(coupon.getCode());
		couponEntity.setPercentage(coupon.getPercentage());
		couponEntity.setMinQuantityProducts(coupon.getMinQuantityProducts());
		couponEntity.setMinOrderValue(coupon.getMinOrderValue());
		couponEntity.setMaxDiscount(coupon.getMaxDiscount());
		couponEntity.setExpiration(coupon.getExpiration());
		return couponEntity;
	}
	
	@Override
	public CouponEntity couponDeactivate(CouponEntity couponEntity) {
		couponEntity.setActive(Boolean.FALSE);
		return couponEntity;
	}
	
	@Override
	public CouponEntity couponActivate(CouponEntity couponEntity) {
		couponEntity.setActive(Boolean.TRUE);
		return couponEntity;
	}

}
