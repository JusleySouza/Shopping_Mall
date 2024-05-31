package com.shopping.mall.discountsapi.app.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shopping.mall.discountsapi.app.service.impl.DiscountServiceImplement;
import com.shopping.mall.discountsapi.domain.model.Discount;
import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

@WebMvcTest(controllers = DiscountController.class)
class DiscountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DiscountServiceImplement service;
	private ObjectMapper objectMapper;
	private Discount discount;
	
	private final String CONTEXT_PATH = "/discounts";
	private final String PATH_FIND_ALL_TYPE = "/types";
	private final String PATH_FIND_DEACTIVATE = "/deactivate";
	private final String PATH_FIND_ACTIVATE = "/activate";
	private final String DISCOUNT_TYPE = "/PROD";
	private final String DISCOUNT_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		discount = ClassBuilder.discountBuilder();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	void save() throws Exception{
		mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(discount)));
	}
	
	@Test
	void update() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + DISCOUNT_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(discount)));
	}
	
	@Test
	void listTypesDiscount() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL_TYPE + DISCOUNT_TYPE)).andExpect(status().isOk());
	}
	
	@Test
	void findById() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + DISCOUNT_ID)).andExpect(status().isOk());
	}
	
	@Test
	void deactivate() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + PATH_FIND_DEACTIVATE + DISCOUNT_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(discount))).andExpect(status().isNoContent());
	}
	
	@Test
	void activate() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + PATH_FIND_ACTIVATE + DISCOUNT_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(discount))).andExpect(status().isNoContent());
	}

}
