package com.shopping.mall.userapi.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
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
	
	@NotEmpty(message = "{street.not.empty}")
	@Size(max = 45, message = "{street.not.greater.than}")
	@ApiModelProperty(notes = "Street", example = "Rua das Amélias", required = true)
	private String street;
	@NotNull(message = "{number.not.null}")
	@Min(value=1, message = "{number.not.less.than}")
	@Max(value=1000000000, message = "{number.not.greater.than}")
	@ApiModelProperty(notes = "Number", example = "1", required = true)
	private int number;
	@NotEmpty(message = "{cep.not.empty}")
	@Pattern(regexp = "([0-9]{5})(\\-)([0-9]{3})", message = "{cep.format}")
	@ApiModelProperty(notes = "Cep", example = "12000-090", required = true)
	private String cep;
	@Size(max=45, message = "{complement.not.greater.than}")
	@ApiModelProperty(notes = "Complement", example = "Casa 2", required = true)
	private String complement;
	@NotEmpty(message = "{city.not.empty}")
	@ApiModelProperty(notes = "City", example = "São Bernardo", required = true)
	private String city;
	@NotEmpty(message = "{state.not.empty}")
	@ApiModelProperty(notes = "State", example = "SP", required = true)
	private String state;
	@NotEmpty(message = "{nickName.not.empty}")
	@ApiModelProperty(notes = "NickName", example = "Rua 7", required = true)
	@Size(min = 1 ,max = 45, message = "{nickName.size}")
	private String nickName;
	@NotEmpty(message = "{neighborhood.not.empty}")
	@Size(max = 45, message = "{neighborhood.not.greater.than}")
	@ApiModelProperty(notes = "Neighborhood", example = "Vila das Aves", required = true)
	private String neighborhood;
	@Size(min = 2, max = 255, message = "{reference.size}")
	@ApiModelProperty(notes = "Reference", example = "Em frente a padaria Nova Alegria", required = false)
	private String reference;
	@JsonProperty("country")
	private CountryDTO countryDTO;
	private Boolean defaultAddress;

}
