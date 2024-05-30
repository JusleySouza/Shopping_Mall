package com.shopping.mall.discountsapi.infra.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.discountsapi.infra.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, UUID> {
	
	CouponEntity findByCode(String code);
	
	CouponEntity findByIdAndActiveTrue(UUID id);
	
	List<CouponEntity> findAllByActiveTrue();
	
	List<CouponEntity> findAllByActiveFalse();

}
