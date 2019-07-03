package com.tradetask.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradetask.entity.Trade;
import com.tradetask.service.TradeService;

@RestController
@RequestMapping(value = "/v2")
public class TradeSearchRestController {
	private static Logger logger = LogManager.getLogger(TradeSearchRestController.class);
	@Autowired
	TradeService tradeService;

	@GetMapping("/trades")
	public List<Trade> getTrades() {
		logger.info("inside List<Trade> getTrades() method ");
		return tradeService.FindAll();
	}

	@GetMapping("/trades/{tradeId}")
	public ResponseEntity<Trade> findByTradeId(@PathVariable(value = "tradeId") int tradeId) {
		logger.info("inside ResponseEntity<Trade> getTrade(@PathVariable(value = \"tradeId\") int tradeId) method ");
		Trade trade = tradeService.findById(tradeId);
		if (trade != null) {
			return new ResponseEntity<Trade>(trade, HttpStatus.FOUND);
		}
		return new ResponseEntity<Trade>(trade, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/trades/listoftrades")
	public List<Trade> getTrades(@RequestBody List<Trade> trades) {
		return tradeService.FindAll(trades);
	}

	@GetMapping("/test")
	public ResponseEntity<Void> getTest() {
		return new ResponseEntity<Void>(HttpStatus.FOUND);
	}
}
