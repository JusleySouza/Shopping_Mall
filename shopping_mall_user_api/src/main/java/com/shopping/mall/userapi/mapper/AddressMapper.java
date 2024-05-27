package com.shopping.mall.userapi.mapper;

import com.shopping.mall.userapi.model.Address;
import com.shopping.mall.userapi.model.dto.AddressDTO;

public class AddressMapper {
	
	public static Address toModel(AddressDTO addressDTO) {
		return Address.builder()
				.street(addressDTO.getStreet())
				.number(addressDTO.getNumber())
				.cep(addressDTO.getCep())
				.complement(addressDTO.getComplement())
				.city(addressDTO.getCity())
				.state(addressDTO.getState())
				.neighborhood(addressDTO.getNeighborhood())
				.defaultAddress(addressDTO.getDefaultAddress())
				.nickName(addressDTO.getNickName())
				.reference(addressDTO.getReference())
				.country(CountryMapper.toModel(addressDTO.getCountryDTO()))
				.build();
	}
	
	public static AddressDTO toDTO(Address address) {
		return AddressDTO.builder()
				.street(address.getStreet())
				.number(address.getNumber())
				.cep(address.getCep())
				.complement(address.getComplement())
				.city(address.getCity())
				.state(address.getState())
				.nickName(address.getNickName())
				.neighborhood(address.getNeighborhood())
				.defaultAddress(address.getDefaultAddress())
				.reference(address.getReference())
				.countryDTO(CountryMapper.toDTO(address.getCountry()))
				.build();
	}

	public static Address updateAddress(Address address, AddressDTO requestAddressDTO) {
		address.setStreet(requestAddressDTO.getStreet());
		address.setNumber(requestAddressDTO.getNumber());
		address.setCep(requestAddressDTO.getCep());
		address.setComplement(requestAddressDTO.getComplement());
		address.setCity(requestAddressDTO.getCity());
		address.setState(requestAddressDTO.getState());
		address.setNickName(requestAddressDTO.getNickName());
		address.setNeighborhood(requestAddressDTO.getNeighborhood());
		address.setDefaultAddress(requestAddressDTO.getDefaultAddress());
		address.setReference(requestAddressDTO.getReference());
		address.setCountry(CountryMapper.updateCountry(address.getCountry(), requestAddressDTO.getCountryDTO()));
		return address;
	}
	

}
