package com.shopping.mall.userapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.userapi.test.utils.ClassBuilder;

class ResponseUserDTOTest {
	
	private ResponseUserDTO expectedResponseUserDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseUserDTO = ClassBuilder.responseUserDTOBuilder();
		expectedResponseUserDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseUserDTO responseUserDTO = ResponseUserDTO.builder()
				.id(id)
				.fullName("Paulo Ricardo Santos")
				.socialName("Paulinho")
				.email("paulo@gmail.com")
				.dateBirth(LocalDate.now().toString())
				.cpf("111.300.458-46")	
				.phone("+055(12)99765-4321")
				.created(LocalDate.now().toString())
				.changed(LocalDate.now().toString())
				.build();
		assertEquals(expectedResponseUserDTO.toString(), responseUserDTO.toString());
	
	}
	
	@Test
	void setter() {
		ResponseUserDTO responseUserDTO = new ResponseUserDTO();
		responseUserDTO.setId(id);
		responseUserDTO.setFullName("Paulo Ricardo Santos");
		responseUserDTO.setSocialName("Paulinho");
		responseUserDTO.setEmail("paulo@gmail.com");
		responseUserDTO.setDateBirth(LocalDate.now().toString());
		responseUserDTO.setCpf("111.300.458-46");	
		responseUserDTO.setPhone("+055(12)99765-4321");
		responseUserDTO.setCreated(LocalDate.now().toString());
		responseUserDTO.setChanged(LocalDate.now().toString());
		assertEquals(expectedResponseUserDTO.toString(), responseUserDTO.toString());
	}

}
