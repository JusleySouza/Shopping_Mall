package com.shopping.mall.discountsapi.app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.mall.discountsapi.app.dto.RequestCouponDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseCouponDTO;

@Service
public interface CouponService {
	
	public ResponseEntity<Object> saveCoupon(RequestCouponDTO requestCouponDTO);
	
	public ResponseEntity<Object> updateCoupon(RequestCouponDTO requestCouponDTO, UUID couponId);
	
	public ResponseEntity<ResponseCouponDTO> deactivateCoupon(UUID couponId);
	
	public ResponseEntity<ResponseCouponDTO> activateCoupon(UUID couponId);
	
	public ResponseCouponDTO findByIdCoupon(UUID id);
	
	public List<ResponseCouponDTO>findAllActivated();
	
	public List<ResponseCouponDTO>findAllDeactivated();
	
	public ResponseCouponDTO findByCodeCoupon(String codeCoupon);
	
}
