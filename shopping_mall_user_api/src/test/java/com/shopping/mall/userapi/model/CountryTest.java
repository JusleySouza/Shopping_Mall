package com.shopping.mall.userapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.userapi.test.utils.ClassBuilder;

class CountryTest {
	private Country expectedCountry;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedCountry = ClassBuilder.countryBuilder();
		expectedCountry.setId(id);
	}

	@Test
	void builder() {
		Country country = Country.builder()
				.id(id)
				.name("Brasil")
				.code("+055")
				.created(LocalDate.now())
				.changed(LocalDate.now())
				.build();
		assertEquals(expectedCountry.toString(), country.toString());
	}
	
	@Test
	void setter() {
		Country country = new Country();
		country.setId(id);
		country.setName("Brasil");
		country.setCode("+055");
		country.setCreated(LocalDate.now());
		country.setChanged(LocalDate.now());
		assertEquals(expectedCountry.toString(), country.toString());
	}

}
