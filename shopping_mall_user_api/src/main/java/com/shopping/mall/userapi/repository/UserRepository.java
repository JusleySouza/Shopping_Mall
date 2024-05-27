package com.shopping.mall.userapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.userapi.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	User findByIdAndActiveTrue(UUID id);
	User findByCpfAndActiveTrue(String cpf);
	List<User> findAllByActiveTrue();
	User findByFullName(String name);
	
}
