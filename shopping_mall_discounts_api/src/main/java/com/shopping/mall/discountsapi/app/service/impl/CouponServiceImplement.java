package com.shopping.mall.discountsapi.app.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.app.dto.RequestCouponDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseCouponDTO;
import com.shopping.mall.discountsapi.app.mapper.CouponMapper;
import com.shopping.mall.discountsapi.app.service.CouponService;
import com.shopping.mall.discountsapi.cross.config.LoggerConfig;
import com.shopping.mall.discountsapi.cross.dto.error.ResponseError;
import com.shopping.mall.discountsapi.cross.exception.ValidationException;
import com.shopping.mall.discountsapi.domain.usecase.ActivateCoupon;
import com.shopping.mall.discountsapi.domain.usecase.DeactivateCoupon;
import com.shopping.mall.discountsapi.domain.usecase.FindAllActivatedCoupon;
import com.shopping.mall.discountsapi.domain.usecase.FindAllDeactivatedCoupon;
import com.shopping.mall.discountsapi.domain.usecase.FindByCodeCoupon;
import com.shopping.mall.discountsapi.domain.usecase.FindByIdCoupon;
import com.shopping.mall.discountsapi.domain.usecase.SaveCoupon;
import com.shopping.mall.discountsapi.domain.usecase.UpdateCoupon;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Component
public class CouponServiceImplement implements CouponService {
	
	private final SaveCoupon saveCoupon;
	private final UpdateCoupon updateCoupon;
	private final DeactivateCoupon deactivateCoupon;
	private final ActivateCoupon activateCoupon;
	private final FindByIdCoupon findByIdCoupon;
	private final FindAllActivatedCoupon findAllActivatedCoupon;
	private final FindAllDeactivatedCoupon findAllDeactivatedCoupon;
	private final FindByCodeCoupon findByCodeCoupon;
    private final CouponMapper mapper;
	private final Validator validator;
	private ResponseCouponDTO responseCouponDTO;
	private List<ResponseCouponDTO> listResponseCouponDTO;

	@Override
	public ResponseEntity<Object> saveCoupon(RequestCouponDTO requestCouponDTO) {
		Set<ConstraintViolation<RequestCouponDTO>> violations = validator.validate(requestCouponDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_COUPON.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if(requestCouponDTO.getExpiration().isBefore(LocalDate.now())) {
			LoggerConfig.LOGGER_COUPON.error("Validation error!");
			throw new ValidationException("The date must be later than the current date.");
		}
		
		mapper.modelToResponseCouponDTO(saveCoupon.save(mapper.toModel(requestCouponDTO)));
		
		LoggerConfig.LOGGER_COUPON.info("Coupon salved successfully!");
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	
	@Override
	public ResponseEntity<Object> updateCoupon(RequestCouponDTO requestCouponDTO, UUID couponId) {
		Set<ConstraintViolation<RequestCouponDTO>> violations = validator.validate(requestCouponDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_COUPON.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if(requestCouponDTO.getExpiration().isBefore(LocalDate.now())) {
			LoggerConfig.LOGGER_COUPON.error("Validation error!");
			throw new ValidationException("The date must be later than the current date.");
		}
		
		mapper.modelToResponseCouponDTO(updateCoupon.update(mapper.toModel(requestCouponDTO), couponId));
		
		LoggerConfig.LOGGER_COUPON.info("Coupon updated successfully!");
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResponseCouponDTO> deactivateCoupon(UUID couponId) {
		
		mapper.modelToResponseCouponDTO(deactivateCoupon.deactivateCoupon(couponId));
		
		LoggerConfig.LOGGER_COUPON.info("Coupon deactivated successfully!");
		
		return new ResponseEntity<ResponseCouponDTO>(HttpStatus.NO_CONTENT);
	}
	
	
	@Override
	public ResponseEntity<ResponseCouponDTO> activateCoupon(UUID couponId) {
		
		mapper.modelToResponseCouponDTO(activateCoupon.activate(couponId));
		
		LoggerConfig.LOGGER_COUPON.info("Coupon activated successfully!");
		
		return new ResponseEntity<ResponseCouponDTO>(HttpStatus.NO_CONTENT);
	}


	@Override
	public ResponseCouponDTO findByIdCoupon(UUID id) {
		
		responseCouponDTO = mapper.modelToResponseCouponDTO(findByIdCoupon.findById(id));
		
		LoggerConfig.LOGGER_COUPON.info("Coupon found successfully!");
		
		return responseCouponDTO;
	}
	
	@Override
	public List<ResponseCouponDTO> findAllActivated() {
		
		listResponseCouponDTO = mapper.modelToListResponseCouponDTO(findAllActivatedCoupon.findAll());
		
		LoggerConfig.LOGGER_COUPON.info("Coupon found successfully!");
		
		return listResponseCouponDTO;
	}


	@Override
	public List<ResponseCouponDTO> findAllDeactivated() {

		listResponseCouponDTO = mapper.modelToListResponseCouponDTO(findAllDeactivatedCoupon.findAllDeactivated());
		
		LoggerConfig.LOGGER_COUPON.info("Coupon found successfully!");
		
		return listResponseCouponDTO;
	}


	@Override
	public ResponseCouponDTO findByCodeCoupon(String codeCoupon) {

		responseCouponDTO = mapper.modelToResponseCouponDTO(findByCodeCoupon.findByCode(codeCoupon));
		
		LoggerConfig.LOGGER_COUPON.info("Coupon found successfully!");
		
		return responseCouponDTO;
	}

}
