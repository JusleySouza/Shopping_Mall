package com.shopping.mall.userapi.test.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.shopping.mall.userapi.model.Address;
import com.shopping.mall.userapi.model.Country;
import com.shopping.mall.userapi.model.User;
import com.shopping.mall.userapi.model.dto.AddressDTO;
import com.shopping.mall.userapi.model.dto.CountryDTO;
import com.shopping.mall.userapi.model.dto.RequestUserDTO;
import com.shopping.mall.userapi.model.dto.ResponseUserDTO;

public final class ClassBuilder {

	public static Address addressBuilder() {
		Address address = new Address();
		address.setId(UUID.randomUUID());
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
		address.setCountry(countryBuilder());
		return address;
	}
	
	public static Country countryBuilder() {
		Country country = new Country();
		country.setId(UUID.randomUUID());
		country.setName("Brasil");
		country.setCode("+055");
		country.setCreated(LocalDate.now());
		country.setChanged(LocalDate.now());
		return country;
	}
	
	public static User userBuilder() {
		User user = new User();
		user.setId(UUID.randomUUID());
		user.setFullName("Paulo Ricardo Santos");
		user.setSocialName("Paulinho");
		user.setEmail("paulo@gmail.com");
		user.setDateBirth(LocalDate.now());
		user.setCpf("111.300.458-46");	
		user.setPhone("+055(12)99765-4321");
		user.setCreated(LocalDate.now());
		user.setChanged(LocalDate.now());
		user.setActive(Boolean.TRUE);
		user.setListAddress(List.of(addressBuilder()));		
		return user;
	}
	
	public static AddressDTO addressDTOBuilder() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setStreet("Rua Pedro Maciel");
		addressDTO.setNumber(50);
		addressDTO.setCep("08997-065");
		addressDTO.setComplement("Casa 2");
		addressDTO.setCity("São Paulo");
		addressDTO.setState("SP");
		addressDTO.setNickName("Rua 7");
		addressDTO.setNeighborhood("Vila Mariana");
		addressDTO.setCountryDTO(countryDTOBuilder());
		addressDTO.setDefaultAddress(Boolean.TRUE);
		addressDTO.setReference("Em frente a padaria Nova Alegria");
		return addressDTO;	
		
	}
	
	public static CountryDTO countryDTOBuilder() {
		CountryDTO countryDTO = new CountryDTO();
		countryDTO.setName("Brasil");
		countryDTO.setCode("+055");
		return countryDTO;
	}
	
	public static RequestUserDTO requestUserDTOBuilder() {
		RequestUserDTO requestUserDTO = new RequestUserDTO();
		requestUserDTO.setFullName("Paulo Ricardo Santos");
		requestUserDTO.setSocialName("Paulinho");
		requestUserDTO.setEmail("paulo@gmail.com");
		requestUserDTO.setDateBirth(LocalDate.now());
		requestUserDTO.setCpf("111.300.458-46");	
		requestUserDTO.setPhone("+055(12)99765-4321");
		return requestUserDTO;
	}
	
	public static ResponseUserDTO responseUserDTOBuilder() {
		ResponseUserDTO responseUserDTO = new ResponseUserDTO();
		responseUserDTO.setId(UUID.randomUUID());
		responseUserDTO.setFullName("Paulo Ricardo Santos");
		responseUserDTO.setSocialName("Paulinho");
		responseUserDTO.setEmail("paulo@gmail.com");
		responseUserDTO.setDateBirth(LocalDate.now().toString());
		responseUserDTO.setCpf("111.300.458-46");	
		responseUserDTO.setPhone("+055(12)99765-4321");
		responseUserDTO.setCreated(LocalDate.now().toString());
		responseUserDTO.setChanged(LocalDate.now().toString());
		return responseUserDTO;
	}
	

}
