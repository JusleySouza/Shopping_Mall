package com.shopping.mall.userapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.userapi.test.utils.ClassBuilder;

class AddressDTOTest {
	private AddressDTO expectedAddressDTO;
	private CountryDTO countryDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedAddressDTO = ClassBuilder.addressDTOBuilder();
		countryDTO = ClassBuilder.countryDTOBuilder();
		expectedAddressDTO.setCountryDTO(countryDTO);
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
				.countryDTO(countryDTO)
				.neighborhood("Vila Mariana")
				.nickName("Rua 7")
				.reference("Em frente a padaria Nova Alegria")
				.defaultAddress(Boolean.TRUE)
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
		addressDTO.setNeighborhood("Vila Mariana");
		addressDTO.setReference("Em frente a padaria Nova Alegria");
		addressDTO.setCountryDTO(countryDTO);
		addressDTO.setDefaultAddress(Boolean.TRUE);
		assertEquals(expectedAddressDTO.toString(), addressDTO.toString());
	}

}
