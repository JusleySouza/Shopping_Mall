package com.shopping.mall.userapi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shopping.mall.userapi.controller.UserController;
import com.shopping.mall.userapi.model.User;
import com.shopping.mall.userapi.services.implement.UsersServicesImplement;
import com.shopping.mall.userapi.test.utils.ClassBuilder;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UsersServicesImplement service;
	private ObjectMapper objectMapper;
	private User user;
	
	private final String CONTEXT_PATH = "/users";
	private final String PATH_FIND_ALL = "/list";
	private final String PATH_FIND_NAME = "/name";
	private final String PATH_FIND_CPF = "/cpf";
	private final String USER_CPF = "/111.300.458-46";
	private final String USER_NAME = "/Paulo";
	private final String USER_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		user = ClassBuilder.userBuilder();
		objectMapper.registerModule(new JavaTimeModule());
	}
	

	@Test
	void listUsers() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL)).andExpect(status().isOk());
	}
	
	@Test
	void findByCpf() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_CPF + USER_CPF)).andExpect(status().isOk());
	}
	
	@Test
	void findByName() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_NAME + USER_NAME)).andExpect(status().isOk());
	}
	
	@Test
	void findById() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + USER_ID)).andExpect(status().isOk());
	}
	
	@Test
	void create() throws Exception{
		mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
	}
	
	@Test
	void update() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + USER_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
	}
	
	@Test
	void delete() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete(CONTEXT_PATH + USER_ID)).andExpect(status().isNoContent());
	}

	@Test
	void reativate() throws Exception{
		mockMvc.perform(patch(CONTEXT_PATH + USER_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isNoContent());
	}
}
