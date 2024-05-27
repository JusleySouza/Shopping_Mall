package com.shopping.mall.userapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.mall.userapi.test.utils.ClassBuilder;

class UserTest {
	
	private User expectedUser;
	private UUID id;
	private Address address;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		address = ClassBuilder.addressBuilder();
		expectedUser = ClassBuilder.userBuilder();
		expectedUser.setId(id);
		expectedUser.setListAddress(List.of(address));
	}

	@Test
	void builder() {
		User user = User.builder()
				.id(id)
				.fullName("Paulo Ricardo Santos")
				.socialName("Paulinho")
				.email("paulo@gmail.com")
				.dateBirth(LocalDate.now())
				.cpf("111.300.458-46")
				.phone("+055(12)99765-4321")
				.created(LocalDate.now())
				.changed(LocalDate.now())
				.active(Boolean.TRUE)
				.listAddress(List.of(address))
				.build();
		assertEquals(expectedUser.toString(), user.toString());
	}
	
	@Test
	void setter() {
		User user = new User();
		user.setId(id);
		user.setFullName("Paulo Ricardo Santos");
		user.setSocialName("Paulinho");
		user.setEmail("paulo@gmail.com");
		user.setDateBirth(LocalDate.now());
		user.setCpf("111.300.458-46");
		user.setPhone("+055(12)99765-4321");
		user.setCreated(LocalDate.now());
		user.setChanged(LocalDate.now());
		user.setActive(Boolean.TRUE);
		user.setListAddress(List.of(address));
		assertEquals(expectedUser.toString(), user.toString());
	}

}
