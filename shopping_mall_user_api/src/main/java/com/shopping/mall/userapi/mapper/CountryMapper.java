package com.shopping.mall.userapi.mapper;

import java.time.LocalDate;

import com.shopping.mall.userapi.model.Country;
import com.shopping.mall.userapi.model.dto.CountryDTO;

public class CountryMapper {
	
	public static Country toModel(CountryDTO countryDTO) {
		return Country.builder()
				.name(countryDTO.getName())
				.code(countryDTO.getCode())
				.created(LocalDate.now())
				.build();
	}
	
	public static CountryDTO toDTO(Country country) {
		return CountryDTO.builder()
				.name(country.getName())
				.code(country.getCode())
				.build();
	}
	

	public static Country updateCountry(Country country, CountryDTO requestCountryDTO) {
		country.setName(requestCountryDTO.getName());
		country.setCode(requestCountryDTO.getCode());
		country.setChanged(LocalDate.now());
		return country;
	}

}
