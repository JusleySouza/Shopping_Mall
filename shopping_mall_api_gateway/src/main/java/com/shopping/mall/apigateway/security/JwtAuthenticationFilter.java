package com.shopping.mall.apigateway.security;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;
import com.shopping.mall.apigateway.enums.Role;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

	private static final String SECRET_KEY = "Som35ecretK3y109jP2n8PaMS05mDKAPOjd23ur98y0ej";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
				.filter(authHeader -> authHeader.startsWith("Bearer "))
				.map(authHeader -> authHeader.replace("Bearer ", "")).map(this::getRoleFromToken)
				.defaultIfEmpty(Role.ANONYMOUS)
				.filter(role -> hasPermission(role, exchange.getRequest().getURI().getPath(),
						exchange.getRequest().getMethod()))
				.flatMap(role -> chain.filter(exchange))
				.switchIfEmpty(Mono.defer(() -> onError(exchange, "Accesso negado", HttpStatus.UNAUTHORIZED)));
	}

	@Override
	public int getOrder() {
		return -100;
	}

	public Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		return response.setComplete();
	}

	private boolean hasPermission(Role role, String path, HttpMethod method) {
		return role.getPermissions(method).stream()
				.anyMatch(permittedPath -> new AntPathMatcher().match(permittedPath, path));
	}

	private Role getRoleFromToken(String token) {
		log.info("Token: {}", token);
		try {
			return Role
					.valueOf(Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
							.build().parseClaimsJws(token).getBody().get("role", String.class));
		} catch (JwtException e) {
			log.warn("Erro ao extrair token: {}", e.getMessage());
			return Role.ANONYMOUS;
		}
	}

}
