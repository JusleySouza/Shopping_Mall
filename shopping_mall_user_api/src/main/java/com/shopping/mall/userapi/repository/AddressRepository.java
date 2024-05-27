package com.shopping.mall.userapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.userapi.model.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {
	
	Address findByNickName(String nickName);
	
	List<Address> findByUserId(UUID id);
	
}
