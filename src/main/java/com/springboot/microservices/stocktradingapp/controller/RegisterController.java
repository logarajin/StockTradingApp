package com.springboot.microservices.stocktradingapp.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.microservices.stocktradingapp.StockTradingAppApplication;
import com.springboot.microservices.stocktradingapp.domain.User;
import com.springboot.microservices.stocktradingapp.services.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	

	private static final Logger logger = LoggerFactory.getLogger(StockTradingAppApplication.class);	
	

	@Autowired
	private RegisterService registerService;

	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String redirToList() {
		return "redirect:/register/main";
	}

	@RequestMapping("/main")
	public String mainPage(Model model) {

		logger.debug("--RegisterController mainPage forwaring to main.html --");

		return "register/main";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		logger.debug("--RegisterController /register forwaring to register.html --");
		
		User user = new User();

		model.addAttribute("user", user);

		return "register/register";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public String saveEmployee(User user, Model model) {
		logger.debug("--RegisterController /add forwaring to success.html --");
		
		User savedUser = registerService.save(user);

		model.addAttribute("user", savedUser);
		return "/register/success";
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST, produces = "application/json")
	public String loginCheck(User user, Model model) {
		logger.debug("--RegisterController /loginCheck Entering --");
		
		String result = registerService.getUser(user);

		model.addAttribute("result", result);
		
		logger.debug("--RegisterController /loginCheck result ="+result);

		if (result.equals("success")) {
			logger.debug(result+"=>Registered User.Forwarding to Trade Page --");
			session.setAttribute("user", user);
			model.addAttribute("user", user);
			return "redirect:/trade/trade";
		} else {
			logger.debug(result+"==>User is not registered.Forwarding to Error Page --");
			return "/register/error";
		}
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		logger.debug("--RegisterController /login forwarding to login screen =");
		User user = new User();

		model.addAttribute("user", user);

		return "register/login";
	}

}