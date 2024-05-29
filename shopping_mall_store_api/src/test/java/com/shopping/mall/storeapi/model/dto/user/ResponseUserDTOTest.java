package com.shopping.mall.storeapi.model.dto.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class ResponseUserDTOTest {
	
	private UUID id;
	private ResponseUserDTO expectedResponseUserDTO;

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
				.name("Paulo")
				.lastName("Souza")
				.cpf("111.300.458-46")	
				.phone("+055(12)99765-4321")
				.created(LocalDate.now())
				.changed(LocalDate.now())
				.addressDTO(ClassBuilder.addressDTOBuilder())
				.build();
		assertEquals(expectedResponseUserDTO.toString(), responseUserDTO.toString());
	}

	@Test
	void setter() {
		ResponseUserDTO responseUserDTO = new ResponseUserDTO();
		responseUserDTO.setId(id);
		responseUserDTO.setName("Paulo");
		responseUserDTO.setLastName("Souza");
		responseUserDTO.setCpf("111.300.458-46");	
		responseUserDTO.setPhone("+055(12)99765-4321");
		responseUserDTO.setCreated(LocalDate.now());
		responseUserDTO.setChanged(LocalDate.now());
		responseUserDTO.setAddressDTO(ClassBuilder.addressDTOBuilder());
		assertEquals(expectedResponseUserDTO.toString(), responseUserDTO.toString());
	}

}
