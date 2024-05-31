package com.shopping.mall.discountsapi.test.utils;

import java.time.LocalDate;
import java.util.UUID;

import com.shopping.mall.discountsapi.app.dto.RequestCouponDTO;
import com.shopping.mall.discountsapi.app.dto.RequestDiscountDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseCouponDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseDiscountDTO;
import com.shopping.mall.discountsapi.app.enums.TypeDiscount;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.infra.entity.CouponEntity;
import com.shopping.mall.discountsapi.infra.entity.DiscountEntity;

public class ClassBuilder {
	
	public static Coupon couponBuilder() {
		Coupon coupon = new Coupon();
		coupon.setId(UUID.randomUUID());
		coupon.setCode("30OFF");
		coupon.setPercentage(0.3);
		coupon.setMinQuantityProducts(4);
		coupon.setMinOrderValue(15.99);
		coupon.setMaxDiscount(450.99);
		coupon.setCreated(LocalDate.now());
		coupon.setExpiration(LocalDate.now());
		coupon.setActive(Boolean.TRUE);
		return coupon;
	}
	
	public static Discount discountBuilder() {
		Discount discount = new Discount();
		discount.setId(UUID.randomUUID());
		discount.setTypeDiscount("PROD");
		discount.setIdObjectDiscount(UUID.randomUUID());
		discount.setPercentageDiscount(0.4);
		discount.setCreated(LocalDate.now());
		discount.setExpiration(LocalDate.now());
		discount.setActive(Boolean.TRUE);
		return discount;
	}
	
	public static RequestCouponDTO requestCouponDTOBuilder() {
		RequestCouponDTO requestCouponDTO = new RequestCouponDTO();
		requestCouponDTO.setCode("30OFF");
		requestCouponDTO.setPercentage(0.3);
		requestCouponDTO.setMinQuantityProducts(4);
		requestCouponDTO.setMinOrderValue(15.99);
		requestCouponDTO.setMaxDiscount(450.99);
		requestCouponDTO.setExpiration(LocalDate.now());
		return requestCouponDTO;
	}
	
	public static RequestDiscountDTO requestDiscountDTOBuilder() {
		RequestDiscountDTO requestDiscountDTO = new RequestDiscountDTO();
		requestDiscountDTO.setTypeDiscount(TypeDiscount.PROD);
		requestDiscountDTO.setIdObjectDiscount(UUID.randomUUID());
		requestDiscountDTO.setPercentageDiscount(0.4);
		requestDiscountDTO.setExpiration(LocalDate.now());
		return requestDiscountDTO;
	}
	
	public static ResponseCouponDTO responseCouponDTOBuilder() {
		ResponseCouponDTO responseCouponDTO = new ResponseCouponDTO();
		responseCouponDTO.setId(UUID.randomUUID());
		responseCouponDTO.setCode("30OFF");
		responseCouponDTO.setPercentage(0.3);
		responseCouponDTO.setMinQuantityProducts(4);
		responseCouponDTO.setMinOrderValue(15.99);
		responseCouponDTO.setMaxDiscount(450.99);
		responseCouponDTO.setCreated("2023-08-28");
		responseCouponDTO.setExpiration("2024-09-28");
		responseCouponDTO.setActive(Boolean.TRUE);
		return responseCouponDTO;
	}
	
	public static ResponseDiscountDTO responseDiscountDTOBuilder() {
		ResponseDiscountDTO responseDiscountDTO = new ResponseDiscountDTO();
		responseDiscountDTO.setId(UUID.randomUUID());
		responseDiscountDTO.setTypeDiscount(TypeDiscount.PROD);
		responseDiscountDTO.setIdObjectDiscount(UUID.randomUUID());
		responseDiscountDTO.setPercentageDiscount(0.4);
		responseDiscountDTO.setCreated("2023-08-28");
		responseDiscountDTO.setExpiration("2024-08-28");
		responseDiscountDTO.setActive(Boolean.TRUE);
		return responseDiscountDTO;
	}
	
	public static CouponEntity couponEntityBuilder() {
		CouponEntity couponEntity = new CouponEntity();
		couponEntity.setId(UUID.randomUUID());
		couponEntity.setCode("30OFF");
		couponEntity.setPercentage(0.3);
		couponEntity.setMinQuantityProducts(4);
		couponEntity.setMinOrderValue(15.99);
		couponEntity.setMaxDiscount(450.99);
		couponEntity.setCreated(LocalDate.now());
		couponEntity.setExpiration(LocalDate.now());
		couponEntity.setActive(Boolean.TRUE);
		return couponEntity;
	}
	
	public static DiscountEntity discountEntityBuilder() {
		DiscountEntity discountEntity = new DiscountEntity();
		discountEntity.setId(UUID.randomUUID());
		discountEntity.setTypeDiscount("PROD");
		discountEntity.setIdObjectDiscount(UUID.randomUUID());
		discountEntity.setPercentageDiscount(0.4);
		discountEntity.setCreated(LocalDate.now());
		discountEntity.setExpiration(LocalDate.now());
		discountEntity.setActive(Boolean.TRUE);
		return discountEntity;
	}

}
