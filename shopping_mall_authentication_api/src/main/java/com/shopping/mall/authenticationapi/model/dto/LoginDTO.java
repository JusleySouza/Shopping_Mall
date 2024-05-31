package com.shopping.mall.authenticationapi.model.dto;

import lombok.Builder;

@Builder
public record LoginDTO( String email, String password) {

}
