package com.shopping.mall.productsapi.controllers;

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
import com.shopping.mall.productsapi.controller.CategoryController;
import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.services.implement.CategoryServicesImplement;
import com.shopping.mall.productsapi.test.utils.ClassBuilder;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryServicesImplement service;
	private ObjectMapper objectMapper;
	private Category category;
	
	private final String CONTEXT_PATH = "/categories";
	private final String PATH_FIND_ALL = "/list";
	private final String CATEGORY_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";

	@BeforeEach
	void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		category = ClassBuilder.categoryBuilder();
		objectMapper.registerModule(new JavaTimeModule());
	}

	
	@Test
	void listCategories() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL)).andExpect(status().isOk());
	}
	
	
	@Test
	void findById() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + CATEGORY_ID)).andExpect(status().isOk());
	}

	
	@Test
	void create() throws Exception{
		mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category))).andExpect(status().isOk());
	}
	
	
	@Test
	void update() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + CATEGORY_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category))).andExpect(status().isOk());
	}
	
	
	@Test
	void delete() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete(CONTEXT_PATH + CATEGORY_ID)).andExpect(status().isOk());
	}
	
}
