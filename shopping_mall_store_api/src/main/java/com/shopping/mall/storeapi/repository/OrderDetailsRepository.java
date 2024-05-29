package com.shopping.mall.storeapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.storeapi.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, UUID> {
	
	
}
