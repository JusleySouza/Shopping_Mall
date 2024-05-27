package com.shopping.mall.userapi.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.mall.userapi.constants.Conf;

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
public class RequestUserDTO {
	
	@NotEmpty(message = "{fullName.not.empty}")
	@Size(min = 2, max = 255, message = "{fullName.size}")
	@ApiModelProperty(notes = "Full name of the user", example = "Paula dos Santos Medeiros", required = true)
	private String fullName;
	@Size(min = 2, max = 255, message = "{socialName.size}")
	@ApiModelProperty(notes = "User socialName", example = "Paula", required = false)
	private String socialName;
	@NotEmpty(message = "{cpf.not.empty}")
	@CPF(message = "{cpf.not.valid}")
	@ApiModelProperty(notes = "User cpf", example = "112.224.267-06", required = true)
	private String cpf;
	@Pattern(regexp = "(\\+)([0-9]{3})(\\([0-9]{2}\\))([9]{1})?([0-9]{4})-([0-9]{4})", message = "{phone.format}")
	@ApiModelProperty(notes = "User phone", example = "+555(12)9999-9999", required = true)
	private String phone;
	@Email(message = "{email.not.valid}")
	@NotEmpty(message = "{email.not.empty}")
	@ApiModelProperty(notes = "User email", example = "paulasantos@gmail.com", required = true)
	private String email;
	@NotEmpty(message = "{password.not.empty}")
	private String password;
	@DateTimeFormat(pattern = Conf.dateFormat)
	@ApiModelProperty(notes = "User date birth", example = "1995-06-12", required = true)
	private LocalDate dateBirth;
	

}
