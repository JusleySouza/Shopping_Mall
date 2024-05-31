package com.shopping.mall.authenticationapi.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.mall.authenticationapi.model.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, UUID> {
	
	Optional<UserCredential> findByEmail(String email);

}
