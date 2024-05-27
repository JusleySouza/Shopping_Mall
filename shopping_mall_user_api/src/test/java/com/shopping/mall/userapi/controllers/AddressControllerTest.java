package com.shopping.mall.userapi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.shopping.mall.userapi.controller.AddressController;
import com.shopping.mall.userapi.model.Address;
import com.shopping.mall.userapi.services.implement.AddressServicesImplement;
import com.shopping.mall.userapi.test.utils.ClassBuilder;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AddressServicesImplement service;
	private ObjectMapper objectMapper;
	private Address address;
	
	private final String CONTEXT_PATH = "/address/";
	private final String CONTEXT_PATH_CREATE = "/address?userID=";
	private final String PATH_FIND_USER = "user/";
	private final String ADDRESS_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	private final String USER_ID = "40fe9715-93c8-4b13-aefd-e3e28273815a";
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		address = ClassBuilder.addressBuilder();
		objectMapper.registerModule(new JavaTimeModule());
	}

	
	@Test
	void findByIdUser() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_USER + USER_ID)).andExpect(status().isOk());
	}
	
	@Test
	void findByIdAddress() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + ADDRESS_ID)).andExpect(status().isOk());
	}
	
	@Test
	void create() throws Exception{
		mockMvc.perform(post(CONTEXT_PATH_CREATE + USER_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(address))).andExpect(status().isCreated());
	}
	
	
	@Test
	void update() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + ADDRESS_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(address))).andExpect(status().isNoContent());
	}
	
	@Test
	void delete() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete(CONTEXT_PATH + ADDRESS_ID)).andExpect(status().isNoContent());
	}

}
