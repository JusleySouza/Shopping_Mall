package com.shopping.mall.userapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.userapi.test.utils.ClassBuilder;

class RequestUserDTOTest {
	
	private RequestUserDTO expectedRequestUserDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedRequestUserDTO = ClassBuilder.requestUserDTOBuilder();
	}

	@Test
	void builder() {
		RequestUserDTO requestUserDTO = RequestUserDTO.builder()
				.fullName("Paulo Ricardo Santos")
				.socialName("Paulinho")
				.email("paulo@gmail.com")
				.dateBirth(LocalDate.now())
				.cpf("111.300.458-46")	
				.phone("+055(12)99765-4321")
				.build();
		assertEquals(expectedRequestUserDTO.toString(), requestUserDTO.toString());
	}
	
	@Test
	void setter() {
		RequestUserDTO requestUserDTO = new RequestUserDTO();
		requestUserDTO.setFullName("Paulo Ricardo Santos");
		requestUserDTO.setSocialName("Paulinho");
		requestUserDTO.setEmail("paulo@gmail.com");
		requestUserDTO.setDateBirth(LocalDate.now());
		requestUserDTO.setCpf("111.300.458-46");	
		requestUserDTO.setPhone("+055(12)99765-4321");
		assertEquals(expectedRequestUserDTO.toString(), requestUserDTO.toString());
	}

}
