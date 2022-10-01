package com.springboot.microservices.stocktradingapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.microservices.stocktradingapp.StockTradingAppApplication;
import com.springboot.microservices.stocktradingapp.domain.Trade;

@Service
public class TradeServiceImpl implements TradeService {



	private static final Logger logger = LoggerFactory.getLogger(StockTradingAppApplication.class);	
	
	

	private Double balance = 100000.00;

	private static Map<String, Double> tradeMap = new HashMap<>();

    static{
        tradeMap.put("WIPRO", 298.45);
		tradeMap.put("INFY", 949.95);
		tradeMap.put("TCS", 2713.70);
		tradeMap.put("TECHM", 485.8);
    }

	@Override
	public List<Trade> getTradeDetails() {

		logger.debug("--Inside TradeServiceImpl.getTradeDetails --");
		
		List<Trade> tradelist = new ArrayList<>();

		for (Entry<String, Double> entry : tradeMap.entrySet()) {

			Trade objTrade = new Trade(entry.getKey(), entry.getValue());
			tradelist.add(objTrade);
		}
		

		logger.debug("--Inside TradeServiceImpl.getTradeDetails returning --"+tradelist);
		
		return tradelist;
	}

	@Override
	public Trade calculateTrade(Trade trade) {

		logger.debug("--Inside TradeServiceImpl.calculateTrade --"+trade);

		Double price = tradeMap.get(trade.getTicker());
		

		logger.debug("--price=>"+price);

		balance = balance - (trade.getQuantity() * price);
		
		logger.debug("--balance=>"+balance);

		trade.setBalance(balance);
		

		logger.debug("--trade=>"+trade);

		return trade;
	}

}