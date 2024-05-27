package com.shopping.mall.userapi.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {
	
	private UUID id;
	private String fullName;
	private String socialName;
	private String cpf;
	private String email;
	private String phone;
	private String dateBirth;
	private String created;
	private String changed;
	
}
