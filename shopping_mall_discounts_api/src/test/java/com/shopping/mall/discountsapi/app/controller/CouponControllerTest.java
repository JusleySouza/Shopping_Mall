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
import com.shopping.mall.discountsapi.app.service.impl.CouponServiceImplement;
import com.shopping.mall.discountsapi.domain.model.Coupon;
import com.shopping.mall.discountsapi.test.utils.ClassBuilder;

@WebMvcTest(controllers = CouponController.class)
class CouponControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CouponServiceImplement service;
	private ObjectMapper objectMapper;
	private Coupon coupon;
	
	private final String CONTEXT_PATH = "/coupons";
	private final String PATH_FIND_ALL = "/list";
	private final String PATH_FIND_DEACTIVATE = "/deactivate";
	private final String PATH_FIND_DEACTIVATED = "/deactivated";
	private final String PATH_FIND_ACTIVATE = "/activate";
	private final String PATH_FIND_ACTIVATED = "/activated";
	private final String PATH_FIND_CODE = "/code";
	private final String COUPON_CODE = "/30OFF";
	private final String COUPON_ID = "/5c75fcab-9955-4db2-b8b7-4b6c31bfe53d";
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		coupon = ClassBuilder.couponBuilder();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	void listCouponsActivated() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL + PATH_FIND_ACTIVATED)).andExpect(status().isOk());
	}
	
	@Test
	void listCouponsDeactivated() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL + PATH_FIND_DEACTIVATED)).andExpect(status().isOk());
	}
	
	@Test
	void findByCode() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_CODE + COUPON_CODE)).andExpect(status().isOk());
	}

	@Test
	void findById() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + COUPON_ID)).andExpect(status().isOk());
	}
	
	@Test
	void activate() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + PATH_FIND_ACTIVATE + COUPON_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(coupon))).andExpect(status().isNoContent());
	}
	
	@Test
	void deactivate() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + PATH_FIND_DEACTIVATE + COUPON_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(coupon))).andExpect(status().isNoContent());
	}
	
	@Test
	void update() throws Exception{
		mockMvc.perform(put(CONTEXT_PATH + COUPON_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(coupon)));
	}
	
	@Test
	void save() throws Exception{
		mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(coupon)));
	}
	
}
