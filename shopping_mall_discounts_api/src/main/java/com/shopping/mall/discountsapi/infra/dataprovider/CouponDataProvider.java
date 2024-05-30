package com.shopping.mall.discountsapi.infra.dataprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.cross.exception.ResourceNotFoundException;
import com.shopping.mall.discountsapi.cross.exception.ValidationException;
import com.shopping.mall.discountsapi.domain.gateway.CouponGateway;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.infra.entity.CouponEntity;
import com.shopping.mall.discountsapi.infra.mapper.CouponDataProviderMapper;
import com.shopping.mall.discountsapi.infra.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CouponDataProvider implements CouponGateway {

	private final CouponRepository repository;
	private final CouponDataProviderMapper mapper;
	private CouponEntity couponEntity;
	private CouponEntity codeEntity;
	List<CouponEntity> listCoupons;
	List<Coupon> listModel;

	@Override
	public Coupon saveCoupon(Coupon coupon) {

		couponEntity = repository.findByCode(coupon.getCode());

		if (couponEntity != null) {
			throw new ValidationException("A coupon with this code already exists.");
		}

		return mapper.toModel(repository.save(mapper.toEntity(coupon)));
	}

	
	@Override
	public Coupon updateCoupon(Coupon coupon, UUID idCoupon) {

		couponEntity = repository.findById(idCoupon).orElseThrow(() -> new ResourceNotFoundException(
				"Sorry, we were unable to find a coupon with this ID. Check and try again."));

		codeEntity = repository.findByCode(coupon.getCode());

		if (codeEntity != null && !codeEntity.getId().equals(couponEntity.getId())) {
			throw new ValidationException(
					"You already have a coupon registered with this code. Change the code of any of them and try again.");
		}

		return mapper.toModel(repository.save(mapper.updateCoupon(coupon, couponEntity)));
	}

	
	@Override
	public Coupon deactivateCoupon(UUID idCoupon) {
		
		couponEntity = repository.findById(idCoupon).orElseThrow(() -> new ResourceNotFoundException(
				"Sorry, we were unable to find a coupon with this ID. Check and try again."));
		
		if(couponEntity.getActive() == Boolean.FALSE) {
			throw new ValidationException("This coupon is already deactivated.");
		}
		
		return mapper.toModel(repository.save(mapper.couponDeactivate(couponEntity)));
	}
	
	
	@Override
	public Coupon activateCoupon(UUID idCoupon) {
		
		couponEntity = repository.findById(idCoupon).orElseThrow(() -> new ResourceNotFoundException(
				"Sorry, we were unable to find a coupon with this ID. Check and try again."));
		
		if(couponEntity.getActive() == Boolean.TRUE) {
			throw new ValidationException("This coupon is already activated.");
		}
		
		return mapper.toModel(repository.save(mapper.couponActivate(couponEntity)));
	}


	@Override
	public Coupon findByIdCoupon(UUID idCoupon) {

		couponEntity = repository.findByIdAndActiveTrue(idCoupon);
		
		if(couponEntity == null) {
			throw new ResourceNotFoundException("Sorry, we were unable to find a coupon with this ID. Check and try again.");
		}
		
		return mapper.toModel(couponEntity);
	}


	@Override
	public List<Coupon> findAllActivated() {
		listModel = new ArrayList<>();
		listCoupons = repository.findAllByActiveTrue();
		
		for (CouponEntity coupon : listCoupons) {
			listModel.add(mapper.toModel(coupon));
		}
		
		return listModel;
	}


	@Override
	public List<Coupon> findAllDeactivated() {
		listModel = new ArrayList<>();
		listCoupons = repository.findAllByActiveFalse();
		
		for (CouponEntity coupon : listCoupons) {
			listModel.add(mapper.toModel(coupon));
		}
		
		return listModel;
	}


	@Override
	public Coupon findByCodeCoupon(String codeCoupon) {

		couponEntity = repository.findByCode(codeCoupon);
		
		if(couponEntity == null) {
			throw new ResourceNotFoundException("Sorry, we were unable to find a coupon with this code. Check and try again.");
		}
		
		return mapper.toModel(couponEntity);
	}

}
