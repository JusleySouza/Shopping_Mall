package com.shopping.mall.productsapi.controllers;

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
import com.shopping.mall.productsapi.controller.ProductController;
import com.shopping.mall.productsapi.model.Product;
import com.shopping.mall.productsapi.services.implement.ProductServicesImplement;
import com.shopping.mall.productsapi.test.utils.ClassBuilder;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductServicesImplement service;
	private ObjectMapper objectMapper;
	private Product product;
	
	private final String CONTEXT_PATH = "/products";
	private final String PATH_FIND_ALL = "/list";
	private final String PATH_FIND_NAME = "/name";
	private final String PATH_FIND_CATEGORY = "/category";
	private final String PATH_FIND_SUBCATEGORY = "/subcategory";
	private final String PRODUCT_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	private final String CATEGORY_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	private final String SUBCATEGORY_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	private final String PRODUCT_NAME = "/televisão";
	private final String PATH_FIND_SUBTRACTION = "/subtraction";
	private final String PATH_FIND_ADDITION = "/addition";
	private final String PATH_AMOUNT = "?amount=3";

	@BeforeEach
	void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		product = ClassBuilder.productBuilder();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	void listProducts() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL)).andExpect(status().isOk());
	}
	
	
	@Test
	void findById() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PRODUCT_ID)).andExpect(status().isOk());
	}
	
	
	@Test
	void findByName() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_NAME + PRODUCT_NAME)).andExpect(status().isOk());
	}
	
	
	@Test
	void create() throws Exception{
		mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isOk());
	}
	
	
	@Test
	void update() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + PRODUCT_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isOk());
	}
	
	
	@Test
	void delete() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete(CONTEXT_PATH + PRODUCT_ID)).andExpect(status().isNoContent());
	}

	@Test
	void findByCategory() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_CATEGORY + CATEGORY_ID)).andExpect(status().isOk());
	}
	
	@Test
	void findBySubCategory() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_SUBCATEGORY + SUBCATEGORY_ID)).andExpect(status().isOk());
	}
	
	@Test
	void reativate() throws Exception{
		mockMvc.perform(patch(CONTEXT_PATH + PRODUCT_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isNoContent());
	}
	
	@Test
	void subtraction() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + PATH_FIND_SUBTRACTION + PRODUCT_ID + PATH_AMOUNT).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isNoContent());
	}
	
	@Test
	void addition() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + PATH_FIND_ADDITION + PRODUCT_ID + PATH_AMOUNT).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isNoContent());
	}
}
