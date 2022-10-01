package com.springboot.microservices.tradingapp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.springboot.microservices.stocktradingapp.controller.RegisterController;
import com.springboot.microservices.stocktradingapp.domain.User;
import com.springboot.microservices.stocktradingapp.services.RegisterService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockTradingAppApplication.class)
public class RegisterControllerTest {

	private MockMvc mockMvc;

	@Mock
	private Model model;

	@Mock
	private HttpSession session;

	@Autowired
	private RegisterService registerServiceImpl;

	@Mock
	private RegisterService registerService;

	@InjectMocks
	private RegisterController registerController;

	User user = null;

	User user2 = null;

	@Before
	public void setUp() {

		user = new User();
		user.setId(1L);
		user.setUserId("RAM");
		user.setPassword("Ram123");
		user.setEmail("raja.jaganathan@wipro.com");

		user2 = new User(2L, "RajaJ", "test123", "raja.jaganathan@gmail.com");

		mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
	}

	@Test
	public void testRegister() throws Exception {
		this.mockMvc.perform(get("/register/")).andExpect(view().name("redirect:/register/main")).andDo(print());
	}

	@Test
	public void testRegisterMainPage() throws Exception {
		this.mockMvc.perform(get("/register/main")).andExpect(status().isOk()).andExpect(view().name("register/main"))
				.andDo(print());
	}

	@Test
	public void testRegisterPage() throws Exception {
		this.mockMvc.perform(get("/register/register")).andExpect(status().isOk())
				.andExpect(view().name("register/register")).andDo(print());
	}

	@Test
	public void testLogin() throws Exception {
		this.mockMvc.perform(get("/register/login")).andExpect(status().isOk()).andExpect(view().name("register/login"))
				.andDo(print());
	}

	@Test
	public void addUser() throws Exception {

		// User mock = org.mockito.Mockito.mock(User.class);
		Mockito.when(registerService.save(Mockito.any())).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/register/add")/*
								 * .param("UserId", "RAM") .param("Id", "1L")
								 * .param("Password", "Ram123") .param("Email",
								 * "raja.jaganathan@wipro.com")
								 */;

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		User user = (User) result.getModelAndView().getModel().get("user");

		assertThat(user.getUserId(), is("RAM"));
		assertThat(user.getPassword(), is("Ram123"));
		assertThat(user.getEmail(), is("raja.jaganathan@wipro.com"));
		assertEquals("/register/success", result.getResponse().getForwardedUrl());

	}

	@Test
	public void loginCheck() throws Exception {

		// User mock = org.mockito.Mockito.mock(User.class);
		Mockito.when(registerService.getUser(Mockito.any())).thenReturn("success");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/register/loginCheck")/*
										 * .param("UserId", "RAM") .param("Id",
										 * "1L") .param("Password", "Ram123")
										 * .param("Email",
										 * "raja.jaganathan@wipro.com")
										 */;

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals("/trade/trade?result=success", result.getResponse().getRedirectedUrl());

	}

	@Test
	public void loginCheckFail() throws Exception {

		// User mock = org.mockito.Mockito.mock(User.class);
		Mockito.when(registerService.getUser(Mockito.any())).thenReturn("fail");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register/loginCheck");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals("/register/error", result.getResponse().getForwardedUrl());

	}

	@Test
	public void getUser() throws Exception {

		registerServiceImpl.save(user);

		String result = registerServiceImpl.getUser(user);

		assertThat("success", is(result));

	}

	@Test
	public void wrongUser() throws Exception {

		registerServiceImpl.save(user);

		user.setUserId("BENNY");

		String result = registerServiceImpl.getUser(user);

		assertThat("Invalid user.Please register.", is(result));

		user.setPassword("BENNY1");

		String resultPwd = registerServiceImpl.getUser(user);

		assertThat("Invalid user.Please register.", is(resultPwd));
	}

	@Test
	public void wrongPassword() throws Exception {

		registerServiceImpl.save(user);

		User user1 = new User();
		user1.setUserId("RAM");
		user1.setPassword("RAM");

		String resultPwd = registerServiceImpl.getUser(user1);

		assertThat("Invalid Password.Please enter correct password.", is(resultPwd));

	}

	@Test
	public void saveUser() throws Exception {

		User result = registerServiceImpl.save(user);

		assertThat("RAM", is(result.getUserId()));

	}

	@Test
	public void saveUser2() throws Exception {

		User result = registerServiceImpl.save(user2);

		assertThat(2L, is(result.getId()));

	}

}
