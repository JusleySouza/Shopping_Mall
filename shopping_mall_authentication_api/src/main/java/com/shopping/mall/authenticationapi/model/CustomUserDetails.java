package com.shopping.mall.authenticationapi.model;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.shopping.mall.authenticationapi.enums.Role;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private Set<Role> authorities;
	private boolean enabled;
	
	public CustomUserDetails(UserCredential user) {
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.authorities = Collections.singleton(user.getRole());
		this.enabled = user.isAtivo();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
