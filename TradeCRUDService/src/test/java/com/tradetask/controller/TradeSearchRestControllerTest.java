package com.tradetask.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradetask.entity.Trade;
import com.tradetask.service.Impl.TradeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TradeSearchRestControllerTest {
	private MockMvc mockMvc;

	@Mock
	private TradeServiceImpl tradeServiceImpl;

	@InjectMocks
	private TradeSearchRestController tradeSearchRestController;

	@Before
	public void setup() {

		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(tradeSearchRestController).build();
	}

	@Test
	public void testFindByTradeIdWhenNotFound() throws Exception {
		MockHttpServletResponse response = mockMvc
				.perform(get("/v2/trades/{tradeId}", 1).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		System.out.println("response in /v2/trades/{tradeId} " + response.toString());
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void testFindByTradeIdWhenFound() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(get("/v2/test")).andReturn().getResponse();
		System.out.println("Returned Response " + response.getStatus());
		assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
	}

	@Test
	public void testFindByTradeIdWhenFound1() throws Exception {
		
		when(tradeServiceImpl.findById(123)).thenReturn(new Trade());

		mockMvc.perform(get("/v2/trades/{tradeId}", 123)).andExpect(status().isFound());

		verify(tradeServiceImpl).findById(123);
	}

}