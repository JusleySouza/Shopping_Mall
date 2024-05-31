package com.shopping.mall.authenticationapi.model.dto;

import java.util.UUID;

import com.shopping.mall.authenticationapi.enums.Role;

import lombok.Builder;

@Builder
public record UserCredentialDTO( UUID id, String email, String password, Role role, boolean ativo) {

}
