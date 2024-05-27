package com.shopping.mall.userapi.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

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
public class CountryDTO {
	
	@NotEmpty(message = "{namecountry.not.empty}")
	@Max(value=45, message = "{namecountry.not.greater.than}")
	@ApiModelProperty(notes = "Country name", example = "Brasil", required = true)
	private String name;
	@NotEmpty(message = "{code.not.empty}")
	@Max(value=45, message = "{code.not.greater.than}")
	@ApiModelProperty(notes = "Cep", example = "+055", required = true)
	private String code;

}
