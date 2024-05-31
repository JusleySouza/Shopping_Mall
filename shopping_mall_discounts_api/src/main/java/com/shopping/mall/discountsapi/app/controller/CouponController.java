package com.shopping.mall.discountsapi.app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.mall.discountsapi.app.dto.RequestCouponDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseCouponDTO;
import com.shopping.mall.discountsapi.app.service.CouponService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupons")
public class CouponController {
	
	private final CouponService services;
	
	@PostMapping
	public ResponseEntity<Object> saveCoupon(@RequestBody RequestCouponDTO requestCouponDTO){
		return services.saveCoupon(requestCouponDTO);
	}
	
	@PutMapping("/{couponId}")
	public ResponseEntity<Object> updateCoupon(@RequestBody RequestCouponDTO requestCouponDTO,
			@PathVariable("couponId") UUID couponId) {
		return services.updateCoupon(requestCouponDTO, couponId);
	}
	
	@PutMapping("/deactivate/{couponId}")
	public ResponseEntity<Object> deactivateCoupon(@PathVariable("couponId") UUID couponId){
		services.deactivateCoupon(couponId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/activate/{couponId}")
	public ResponseEntity<Object> activateCoupon(@PathVariable("couponId") UUID couponId){
		services.activateCoupon(couponId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{couponId}")
	public ResponseEntity<ResponseCouponDTO> findById (@PathVariable("couponId") UUID couponId){
		return new ResponseEntity<ResponseCouponDTO>(services.findByIdCoupon(couponId), HttpStatus.OK);
	}
	
	@GetMapping("/list/activated")
	public ResponseEntity<List<ResponseCouponDTO>> findAllActivated(){
		return new ResponseEntity<List<ResponseCouponDTO>>(services.findAllActivated(), HttpStatus.OK);
	}
	
	@GetMapping("/list/deactivated")
	public ResponseEntity<List<ResponseCouponDTO>> findAllDeactivated(){
		return new ResponseEntity<List<ResponseCouponDTO>>(services.findAllDeactivated(), HttpStatus.OK);
	}
	
	@GetMapping("/code/{codeCoupon}")
	public ResponseEntity<ResponseCouponDTO> findByCode (@PathVariable("codeCoupon") String codeCoupon){
		return new ResponseEntity<ResponseCouponDTO>(services.findByCodeCoupon(codeCoupon), HttpStatus.OK);
	}

}
