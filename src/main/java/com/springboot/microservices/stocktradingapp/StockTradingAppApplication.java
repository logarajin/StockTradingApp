package com.springboot.microservices.stocktradingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockTradingAppApplication {

	private static final Logger logger = LoggerFactory.getLogger(StockTradingAppApplication.class);	
	
	public static void main(String[] args) {
		SpringApplication.run(StockTradingAppApplication.class, args);
		
		logger.debug("--Application Started--");
	}
}
