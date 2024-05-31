package com.shopping.mall.discountsapi.infra.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.discountsapi.infra.entity.DiscountEntity;

public interface DiscountRepository extends JpaRepository<DiscountEntity, UUID> {
	
	List<DiscountEntity>findByTypeDiscountAndIdObjectDiscountAndActiveTrue(String typeDiscount, UUID idObjectDiscount);
	
	List<DiscountEntity>findByTypeDiscountAndPercentageDiscountAndExpirationAndActiveTrue(String typeDiscount, Double percentageDiscount, LocalDate expiration);
	
	List<DiscountEntity> findAllByTypeDiscount(String typeDiscount);
	
}
