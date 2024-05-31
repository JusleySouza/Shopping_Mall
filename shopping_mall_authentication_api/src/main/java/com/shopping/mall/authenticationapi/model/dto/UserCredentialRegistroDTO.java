package com.shopping.mall.authenticationapi.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserCredentialRegistroDTO(
		@NotBlank(message = "E-mail is required") @Email String email,
        @NotBlank(message = "Password is required")
		@Size(message = "The password must be between 6 and 255 characters",
		min = 6, max = 8) String password) {

}
