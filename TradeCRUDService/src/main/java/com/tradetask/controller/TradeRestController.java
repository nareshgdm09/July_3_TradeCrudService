package com.tradetask.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradetask.controller.exception.BindingErrorsResponse;
import com.tradetask.entity.Trade;
import com.tradetask.service.TradeService;

@RestController
@RequestMapping(value = "/v1")
public class TradeRestController {
	private static Logger log = LogManager.getLogger(TradeRestController.class);
	@Autowired
	TradeService tradeService;

	@PostMapping(path = "/saveTrade", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Trade> saveTrade(@Valid @RequestBody Trade trade, BindingResult bindingResult) {
		log.info("inside saveTrade(@Valid @RequestBody Trade trade, BindingResult bindingResult) method");

		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (trade == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Trade>(trade, HttpStatus.NOT_IMPLEMENTED);
		}
		this.tradeService.saveTrade(trade);
		return new ResponseEntity<Trade>(trade, HttpStatus.CREATED);
	}

	@PutMapping(path = "/updateTrade", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Trade> updateTrade(@RequestBody Trade tradeDetails) {
		log.info("inside updateTrade(@RequestBody Trade tradeDetails) method ");
		Trade trade = tradeService.findById(tradeDetails.getTradeId());

		if (trade == null) {
			return new ResponseEntity<Trade>(HttpStatus.NOT_FOUND);
		}

		trade.setAmount(tradeDetails.getAmount());
		trade.setBankname(tradeDetails.getBankname());
		trade.setComments(tradeDetails.getComments());
		trade.setCurrency(tradeDetails.getCurrency());
		trade.setLocation(tradeDetails.getLocation());

		Trade updatedtrade = tradeService.saveTrade(trade);
		return new ResponseEntity<Trade>(updatedtrade, HttpStatus.CREATED);
	}

	@DeleteMapping("/trades/{tradeId}")
	public ResponseEntity<Void> deleteTrade(@PathVariable(value = "tradeId") int tradeId) {
		log.info("inside deleteTrade(@PathVariable(value = \"tradeId\") int tradeId) method ");

		tradeService.findById(tradeId);
		this.tradeService.delete(tradeId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/saveMultiTrades", consumes = "application/json", produces = "application/json")
	public List<Trade> saveMultiTrade(@RequestBody List<Trade> trades) {
		log.info("inside saveMultiTrade(@RequestBody List<Trade> trades) method ");
		return tradeService.saveAll(trades);
	}

	@DeleteMapping("/deleteMultiTrade")
	public ResponseEntity<Void> deleteMultiTrade(@RequestBody List<Trade> trades) {
		log.info("inside deleteMultiTrade(@RequestBody List<Trade> trades) method ");

		tradeService.FindAll(trades);
		this.tradeService.delete(trades);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
