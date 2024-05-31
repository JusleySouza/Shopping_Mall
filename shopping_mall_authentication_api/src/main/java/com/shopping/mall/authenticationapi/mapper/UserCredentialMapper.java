package com.shopping.mall.authenticationapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shopping.mall.authenticationapi.enums.Role;
import com.shopping.mall.authenticationapi.model.UserCredential;
import com.shopping.mall.authenticationapi.model.dto.UserCredentialDTO;
import com.shopping.mall.authenticationapi.model.dto.UserCredentialRegistroDTO;

@Component
public class UserCredentialMapper {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserCredential toUserCredential(UserCredentialRegistroDTO userCredentialRegistroDTO, Role role) {
		return UserCredential.builder()
				.email(userCredentialRegistroDTO.email())
				.password(userCredentialRegistroDTO.password())
				.ativo(Boolean.TRUE)
				.role(role)
				.password(passwordEncoder.encode(userCredentialRegistroDTO.password()))
				.build();
	}
	
	public static UserCredentialDTO toUserCredentialDTO(UserCredential userCredential) {
		return UserCredentialDTO.builder()
				.email(userCredential.getEmail())
				.password(userCredential.getPassword())
				.id(userCredential.getId())
				.role(userCredential.getRole())
				.ativo(userCredential.isAtivo())
				.build();
	}

}
