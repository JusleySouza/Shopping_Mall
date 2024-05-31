package com.shopping.mall.authenticationapi.services;

import javax.naming.AuthenticationException;

import org.springframework.stereotype.Service;

import com.shopping.mall.authenticationapi.model.dto.LoginDTO;
import com.shopping.mall.authenticationapi.model.dto.UserCredentialDTO;
import com.shopping.mall.authenticationapi.model.dto.UserCredentialRegistroDTO;

@Service
public interface AuthService {

    UserCredentialDTO createAdmin(UserCredentialRegistroDTO dto);

    UserCredentialDTO createUser(UserCredentialRegistroDTO dto);

    String login(LoginDTO username) throws AuthenticationException;

    String validateToken(String token);

}
