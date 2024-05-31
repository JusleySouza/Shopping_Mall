package com.shopping.mall.discountsapi.app.mapper.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.app.dto.RequestCouponDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseCouponDTO;
import com.shopping.mall.discountsapi.app.mapper.CouponMapper;
import com.shopping.mall.discountsapi.domain.model.Coupon;

import lombok.Generated;

@Generated
@Component
public class CouponMapperImpl implements CouponMapper {
	
	@Override
	public Coupon toModel(RequestCouponDTO requestCouponDTO) {
		return Coupon.builder()
				.code(requestCouponDTO.getCode())
				.percentage(requestCouponDTO.getPercentage())
				.minQuantityProducts(requestCouponDTO.getMinQuantityProducts())
				.minOrderValue(requestCouponDTO.getMinOrderValue())
				.maxDiscount(requestCouponDTO.getMaxDiscount())
				.expiration(requestCouponDTO.getExpiration())
				.created(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
	}
	
	@Override
	public ResponseCouponDTO modelToResponseCouponDTO(Coupon coupon) {
		return ResponseCouponDTO.builder()
				.id(coupon.getId())
				.code(coupon.getCode())
				.percentage(coupon.getPercentage())
				.minQuantityProducts(coupon.getMinQuantityProducts())
				.minOrderValue(coupon.getMinOrderValue())
				.maxDiscount(coupon.getMaxDiscount())
				.created(coupon.getCreated().toString().split(" ")[0])
				.expiration(coupon.getExpiration().toString().split(" ")[0])
				.active(coupon.getActive())
				.build();
	}
	
	@Override
	public List<ResponseCouponDTO> modelToListResponseCouponDTO(List<Coupon> listCoupon) {
		List<ResponseCouponDTO> listResponse = new ArrayList<>();
		for(Coupon coupon : listCoupon) {
			listResponse.add(this.modelToResponseCouponDTO(coupon));
		}
		return listResponse;
	}

}
