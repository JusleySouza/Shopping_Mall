package com.shopping.mall.authenticationapi.services;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;


public interface JwtProvider {
	
	public String generatedToken(String email, List<? extends GrantedAuthority> roles);
	 
	public void validateToken(String token);

}
