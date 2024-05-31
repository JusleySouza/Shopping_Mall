package com.shopping.mall.discountsapi.app.mapper;

import java.util.List;

import com.shopping.mall.discountsapi.app.dto.RequestCouponDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseCouponDTO;
import com.shopping.mall.discountsapi.domain.model.Coupon;

public interface CouponMapper {
	
	public Coupon toModel(RequestCouponDTO requestCouponDTO);
	
	public ResponseCouponDTO modelToResponseCouponDTO(Coupon coupon);
	
	public List<ResponseCouponDTO> modelToListResponseCouponDTO(List<Coupon> listCoupon);

}
