package com.shopping.mall.userapi.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.userapi.test.utils.ClassBuilder;

class CountryDTOTest {
	
	private CountryDTO expectedCountryDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedCountryDTO = ClassBuilder.countryDTOBuilder();
	}

	@Test
	void builder() {
		CountryDTO countryDTO = CountryDTO.builder()
				.name("Brasil")
				.code("+055")
				.build();
		assertEquals(expectedCountryDTO.toString(), countryDTO.toString());
	}
	
	@Test
	void setter() {
		CountryDTO countryDTO = new CountryDTO();
		countryDTO.setName("Brasil");
		countryDTO.setCode("+055");
		assertEquals(expectedCountryDTO.toString(), countryDTO.toString());
	}

}
