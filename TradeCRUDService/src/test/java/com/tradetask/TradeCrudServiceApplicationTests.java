package com.tradetask;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tradetask.controller.exception.ResourceNotFoundException;
import com.tradetask.entity.Trade;
import com.tradetask.service.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeCrudServiceApplicationTests {
	@Autowired
	TradeService tradeService;

	@Test
	public void testFindByIdWhenTradeIsIsNotPresent() {

		Assert.assertTrue(tradeService.findById(123).equals(new Trade()));
	}

}
