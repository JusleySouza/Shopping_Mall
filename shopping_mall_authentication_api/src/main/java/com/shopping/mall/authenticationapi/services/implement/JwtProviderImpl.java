package com.shopping.mall.authenticationapi.services.implement;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.shopping.mall.authenticationapi.services.JwtProvider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProviderImpl implements JwtProvider {

	private static final String SECRET_KEY = "Som35ecretK3y109jP2n8PaMS05mDKAPOjd23ur98y0ej";

	@Override
	public String generatedToken(String email, List<? extends GrantedAuthority> roles) {
		Date inicio = Date.from(Instant.now(Clock.systemUTC()));
		return Jwts.builder().setSubject(email).claim("role", roles.get(0)).setIssuedAt(inicio)
				.setExpiration(new Date(inicio.getTime() + 36000000)).signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	@Override
	public void validateToken(String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}

	private Key getSignKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
	}

}
