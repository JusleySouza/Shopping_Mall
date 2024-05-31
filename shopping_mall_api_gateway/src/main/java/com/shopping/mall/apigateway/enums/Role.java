package com.shopping.mall.apigateway.enums;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority{
	
	ADMIN(Map.of(HttpMethod.GET, Set.of("/**"), 
			HttpMethod.POST, Set.of("/**"), 
			HttpMethod.PUT, Set.of("/**"),
			HttpMethod.DELETE, Set.of("/**"),
			HttpMethod.PATCH, Set.of("/**"))),
	USER(Map.of(HttpMethod.GET, Set.of("/users/{id}", "/products/**", "/address/user/{idUser}", "/orders/**"),
			HttpMethod.POST, Set.of("/address/**", "/orders/**"),
			HttpMethod.PUT, Set.of("/users/**", "/address/**"),
			HttpMethod.PATCH, Set.of("/users/**"),
			HttpMethod.DELETE, Set.of("/users/{id}", "/address/{id}", "/orders/**"))),
	ANONYMOUS(Map.of(HttpMethod.GET, Set.of("/products/**"),
			HttpMethod.POST, Set.of("/users/**","/auth/**")));
	
	private final Map<HttpMethod, Set<String>> permissions;
	
	public static Role getRole(String name) {
		return Stream.of(values()).filter(r -> r.name().equalsIgnoreCase(name))
				.findFirst().orElseGet(() -> ANONYMOUS);
	}
	
	public Set<String> getPermissions(HttpMethod method){
		return permissions.getOrDefault(method, Collections.emptySet());
	}

	@Override
	public String getAuthority() {
		return null;
	}

}
