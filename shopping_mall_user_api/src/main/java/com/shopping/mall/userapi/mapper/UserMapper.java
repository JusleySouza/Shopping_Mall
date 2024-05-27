package com.shopping.mall.userapi.mapper;

import java.time.LocalDate;

import com.shopping.mall.userapi.model.User;
import com.shopping.mall.userapi.model.dto.RequestUserDTO;
import com.shopping.mall.userapi.model.dto.ResponseUserDTO;

public class UserMapper {
	
	public static User toModel(RequestUserDTO requestUserDTO) {
		return User.builder()
				.fullName(requestUserDTO.getFullName())
				.socialName(requestUserDTO.getSocialName())
				.cpf(requestUserDTO.getCpf())
				.phone(requestUserDTO.getPhone())
				.email(requestUserDTO.getEmail())
				.password(requestUserDTO.getPassword())
				.dateBirth(requestUserDTO.getDateBirth())
				.created(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
	}
	
	public static ResponseUserDTO modelToResponseUserDTO(User user) {
		return ResponseUserDTO.builder()
				.id(user.getId())
				.fullName(user.getFullName())
				.socialName(user.getSocialName())
				.cpf(user.getCpf())
				.phone(user.getPhone())
				.email(user.getEmail())
				.dateBirth(user.getDateBirth().toString().split(" ")[0])
				.created(user.getCreated().toString().split(" ")[0])
				.changed(user.getChanged() != null ? user.getChanged().toString().split(" ")[0] : "")
				.build();
	}
	
	public static User userDelete(User user) {
		user.setActive(Boolean.FALSE);
		user.setChanged(LocalDate.now());
		return user;
	}
	
	public static User updateUser(User user, RequestUserDTO requestUserDTO) {
		user.setFullName(requestUserDTO.getFullName());
		user.setSocialName(requestUserDTO.getSocialName());
		user.setPhone(requestUserDTO.getPhone());
		user.setEmail(requestUserDTO.getEmail());
		user.setDateBirth(requestUserDTO.getDateBirth());
		user.setChanged(LocalDate.now());
		return user;
	}

}
