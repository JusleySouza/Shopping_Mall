package com.shopping.mall.storeapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.shopping.mall.storeapi.model.Order;
import com.shopping.mall.storeapi.services.implement.OrderServicesImplement;
import com.shopping.mall.storeapi.utils.ClassBuilder;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderServicesImplement service;
	private ObjectMapper objectMapper;
	private Order order;
	
	private final String CONTEXT_PATH = "/orders";
	private final String PATH_FIND_STATUS = "/status/";
	private final String PATH_FIND_ID_USER = "/user";
	private final String PATH_FIND_ID_ORDER = "/order";
	private final String USER_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	private final String ORDER_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	private final int STATUS = 1;

	@BeforeEach
	void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		order = ClassBuilder.orderBuilder();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	void create() throws Exception{
		mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(order))).andExpect(status().isOk());
	}

	@Test
	void findByIdUser() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ID_USER + USER_ID)).andExpect(status().isOk());
	}
	
	@Test
	void findByStatus() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_STATUS + STATUS)).andExpect(status().isOk());
	}
	
	@Test
	void findByIdOrder() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ID_ORDER + ORDER_ID)).andExpect(status().isOk());
	}
	
	@Test
	void delete() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete(CONTEXT_PATH + ORDER_ID)).andExpect(status().isNoContent());
	}
	
}
