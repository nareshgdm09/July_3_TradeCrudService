package com.tradetask.service.Impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tradetask.controller.exception.ResourceNotFoundException;
import com.tradetask.entity.Trade;
import com.tradetask.repository.TradeRepo;

public class TradeServiceImplTest {

	@InjectMocks
	TradeServiceImpl tradeServiceImpl = new TradeServiceImpl();

	@Mock
	TradeRepo tradeRepo;

	@Mock
	List<Trade> listTrades = new ArrayList<>();

	@Mock
	Trade trade;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testFindByIdnullthrowResourceNotFoundException() {
		when(tradeServiceImpl.findById(1234));
	}

	@Test
	public void testFindByIdWhenTradeIsIsPresent() {
		Optional<Trade> trade = Optional.of(new Trade());
		when(tradeRepo.findById(123)).thenReturn(trade);
		System.out.println(tradeServiceImpl.findById(123));
		Assert.assertEquals(new Trade(), tradeServiceImpl.findById(123));
	}

	@Test
	public void testsaveTrade() {
		trade = new Trade("INR", 300, "comments", "location", "status", "bankname", new Date(), new Date());
		when(tradeRepo.save(trade)).thenReturn(trade);
		Assert.assertEquals(trade, tradeServiceImpl.saveTrade(trade));
	}

	@Test
	public void testFindAll() {
		when(tradeRepo.findAll()).thenReturn(listTrades);
		Assert.assertEquals(listTrades.size(), tradeServiceImpl.FindAll().size());
	}

}
