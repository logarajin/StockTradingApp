package com.springboot.microservices.stocktradingapp.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.microservices.stocktradingapp.StockTradingAppApplication;
import com.springboot.microservices.stocktradingapp.domain.Trade;
import com.springboot.microservices.stocktradingapp.services.TradeService;

@Controller
@RequestMapping("/trade")
public class TradeController {
	

	private static final Logger logger = LoggerFactory.getLogger(StockTradingAppApplication.class);	
	

	@Autowired
	private TradeService tradeService;

	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String redirToList() {
		return "redirect:/trade/trade";
	}

	@RequestMapping("/trade")
	public String mainPage(HttpSession session, Model model) {

		logger.debug("--TradeController /trade forwaring to trademain.html --");
		
		Trade trade = new Trade();

		trade.setTradeList(tradeService.getTradeDetails());
		

		model.addAttribute("trade", trade);

		return "trade/trademain";
	}

	@RequestMapping("/add")
	public String add(Trade trade, Model model) {
		

		logger.debug("--TradeController /add forwaring to trade/success.html --");

		trade.setTradeList(tradeService.getTradeDetails());

		Trade aftertrade = tradeService.calculateTrade(trade);
		


		model.addAttribute("trade",aftertrade);
		model.addAttribute("user", session.getAttribute("user"));


		return "trade/success";
	}

	@RequestMapping("/exit")
	public String exit() {
		logger.debug("--TradeController /exit forwaring to register/login.html --");

		return "/register/login/";
	}

}
