package com.shopping.mall.storeapi.model.dto.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.storeapi.utils.ClassBuilder;

class AddressDTOTest {
	
	private AddressDTO expectedAddressDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedAddressDTO = ClassBuilder.addressDTOBuilder();
	}

	@Test
	void builder() {
		AddressDTO addressDTO = AddressDTO.builder()
				.street("Rua Pedro Maciel")
				.number(50)
				.cep("08997-065")
				.complement("Casa 2")
				.city("São Paulo")
				.state("SP")
				.nickName("Rua 7")
				.countryDTO(ClassBuilder.countryDTOBuilder())
				.build();
		assertEquals(expectedAddressDTO.toString(), addressDTO.toString());
	}

	@Test
	void setter() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setStreet("Rua Pedro Maciel");
		addressDTO.setNumber(50);
		addressDTO.setCep("08997-065");
		addressDTO.setComplement("Casa 2");
		addressDTO.setCity("São Paulo");
		addressDTO.setState("SP");
		addressDTO.setNickName("Rua 7");
		addressDTO.setCountryDTO(ClassBuilder.countryDTOBuilder());
		assertEquals(expectedAddressDTO.toString(), addressDTO.toString());
	}

}
