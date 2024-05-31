package com.shopping.mall.authenticationapi.services.implement;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.shopping.mall.authenticationapi.enums.Role;
import com.shopping.mall.authenticationapi.mapper.UserCredentialMapper;
import com.shopping.mall.authenticationapi.model.UserCredential;
import com.shopping.mall.authenticationapi.model.dto.LoginDTO;
import com.shopping.mall.authenticationapi.model.dto.UserCredentialDTO;
import com.shopping.mall.authenticationapi.model.dto.UserCredentialRegistroDTO;
import com.shopping.mall.authenticationapi.repository.UserCredentialRepository;
import com.shopping.mall.authenticationapi.services.AuthService;
import com.shopping.mall.authenticationapi.services.JwtProvider;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserCredentialRepository repository;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final UserCredentialMapper mapper;

	@Override
	@Transactional
	public UserCredentialDTO createAdmin(UserCredentialRegistroDTO dto) {
		UserCredential admin = mapper.toUserCredential(dto, Role.ADMIN);
		return UserCredentialMapper.toUserCredentialDTO(repository.save(admin));
	}

	@Override
	@Transactional
	public UserCredentialDTO createUser(UserCredentialRegistroDTO dto) {
		UserCredential user = mapper.toUserCredential(dto, Role.USER);
		return UserCredentialMapper.toUserCredentialDTO(repository.save(user));
	}

	@Override
	public String login(LoginDTO dto) throws AuthenticationException {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
		if (authenticate.isAuthenticated()) {
			return jwtProvider.generatedToken(authenticate.getName(),
					(List<? extends GrantedAuthority>) authenticate.getAuthorities());
		} else {
			throw new RuntimeException("Error in generating token");
		}
	}

	@Override
	public String validateToken(String token) {
		jwtProvider.validateToken(token);
		return "Token is valid";
	}
}
