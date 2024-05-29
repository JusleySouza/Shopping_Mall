package com.shopping.mall.storeapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.storeapi.model.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
	
	public List<Order> findByIdUser(UUID idUser);
	
	public List<Order> findByStatus(int status);
	
	
}
