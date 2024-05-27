package com.shopping.mall.userapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.userapi.test.utils.ClassBuilder;

class AddressTest {
	
	private Address expectedAddress;
	private UUID id;
	private Country country;
	private User user;
	
	@BeforeEach
	void setUp() {
		id = UUID.randomUUID();
		country = ClassBuilder.countryBuilder();
		expectedAddress = ClassBuilder.addressBuilder();
		expectedAddress.setId(id);
		expectedAddress.setCountry(country);
		expectedAddress.setUser(user);
	}

	@Test
	void builder() {
		Address address = Address.builder()
				.id(id)
				.street("Rua Pedro Maciel")
				.number(50)
				.cep("08997-065")
				.complement("Casa 2")
				.city("São Paulo")
				.state("SP")
				.neighborhood("Vila Mariana")
				.defaultAddress(Boolean.TRUE)
				.nickName("Rua 7")
				.reference("Em frente a padaria Nova Alegria")
				.country(country)
				.user(user)
				.build();
		assertEquals(expectedAddress.toString(), address.toString());
	}
	
	@Test
	void setter() {
		Address address = new Address();
		address.setId(id);
		address.setStreet("Rua Pedro Maciel");
		address.setNumber(50);
		address.setCep("08997-065");
		address.setComplement("Casa 2");
		address.setCity("São Paulo");
		address.setState("SP");
		address.setNeighborhood("Vila Mariana");
		address.setDefaultAddress(Boolean.TRUE);
		address.setNickName("Rua 7");
		address.setReference("Em frente a padaria Nova Alegria");
		address.setCountry(country);
		address.setUser(user);
		assertEquals(expectedAddress.toString(), address.toString());
	}

}
