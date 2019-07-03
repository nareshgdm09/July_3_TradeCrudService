package com.tradetask.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tradetask.entity.Trade;

public interface TradeService {

	public Trade saveTrade(Trade trade) throws DataAccessException;

	public List<Trade> FindAll();

	public List<Trade> FindAll(List<Trade> trades);

	public Trade findById(int tradeId);

	public void delete(int tradeId);

	public void delete(List<Trade> trades);

	public List<Trade> saveAll(List<Trade> trades);

}
