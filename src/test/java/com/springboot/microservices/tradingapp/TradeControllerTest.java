package com.springboot.microservices.tradingapp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.springboot.microservices.stocktradingapp.StockTradingAppApplication;
import com.springboot.microservices.stocktradingapp.controller.TradeController;
import com.springboot.microservices.stocktradingapp.domain.Trade;
import com.springboot.microservices.stocktradingapp.domain.User;
import com.springboot.microservices.stocktradingapp.services.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockTradingAppApplication.class)
public class TradeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private Model model;

	@Autowired
	private TradeService tradeServiceImpl;

	@Mock
	private HttpSession session;

	@Mock
	private TradeService tradeService;

	@InjectMocks
	private TradeController tradeController;

	User user = null;

	private static Map<String, Double> tradeMap = new LinkedHashMap<String, Double>();
	List<Trade> tradelist = new ArrayList<Trade>();

	@Before
	public void setUp() {

		tradeMap.put("WIPRO", 298.45);
		tradeMap.put("INFY", 949.95);
		tradeMap.put("TCS", 2713.70);
		tradeMap.put("TECHM", 485.8);

		for (Entry<String, Double> entry : tradeMap.entrySet()) {

			Trade objTrade = new Trade(entry.getKey(), entry.getValue());
			tradelist.add(objTrade);
		}
		mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
	}
	
	
	@Test
	public void testRedirToList() throws Exception {
		this.mockMvc.perform(get("/trade/")).andExpect(view().name("redirect:/trade/trade"))
				.andDo(print());
	}

	@Test
	public void testRegisterMainPage() throws Exception {
		this.mockMvc.perform(get("/trade/exit")).andExpect(view().name("/register/login/"))
				.andDo(print());
	}

	@Test
	public void mainTrade() throws Exception {

		Mockito.when(tradeService.getTradeDetails()).thenReturn(tradelist);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/trade/trade");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		Trade tradeModel = (Trade) result.getModelAndView().getModelMap().get("trade");

		Trade trade = tradeModel.getTradeList().get(0);

		assertThat(trade.getTicker(), is("WIPRO"));
		assertThat(trade.getPrice(), is(298.45));

		assertEquals("trade/trademain", result.getResponse().getForwardedUrl());

	}

	@Test
	public void calculateTrade() throws Exception {
		tradelist.get(1).setQuantity(new Double(10));

		tradelist.get(1).setBalance(new Double(87516.0));

		Mockito.when(tradeService.calculateTrade(Mockito.any())).thenReturn(tradelist.get(1));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/trade/add");

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(view().name("trade/success")).andDo(print())
				.andReturn();

		Trade tradeModel = (Trade) result.getModelAndView().getModelMap().get("trade");

		assertThat(tradeModel.getTicker(), is("INFY"));
		assertThat(tradeModel.getPrice(), is(949.95));
		assertThat(tradeModel.getBalance(), is(87516.0));

		assertEquals("trade/success", result.getResponse().getForwardedUrl());

	}

	@Test
	public void getTradeDetails() throws Exception {

		List<Trade> tradeList = tradeServiceImpl.getTradeDetails();

		assertThat(tradelist.size(), is(tradeList.size()));

	}

	@Test
	public void calculateTradeTest() throws Exception {

		tradelist.get(1).setQuantity(new Double(10));

		tradelist.get(1).setTicker("WIPRO");

		Trade trade = tradeServiceImpl.calculateTrade(tradelist.get(1));

		assertThat(97015.5, is(trade.getBalance()));

	}
	
	
	@Test
	public void calculateTradeTestTcs() throws Exception {

		tradelist.get(2).setQuantity(new Double(10));

		tradelist.get(2).setTicker("TCS");
		

		tradelist.get(2).setPrice(3000.00);

		Trade trade = tradeServiceImpl.calculateTrade(tradelist.get(2));

		assertThat(69878.5, is(trade.getBalance()));

	}
}
