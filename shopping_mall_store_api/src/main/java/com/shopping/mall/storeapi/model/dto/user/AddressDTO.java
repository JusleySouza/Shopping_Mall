package com.shopping.mall.storeapi.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	

	private String street;
	private int number;
	private String cep;
	private String complement;
	private String city;
	private String state;
	private String nickName;
	@JsonProperty("country")
	private CountryDTO countryDTO;

}
