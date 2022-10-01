package com.springboot.microservices.stocktradingapp.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.microservices.stocktradingapp.StockTradingAppApplication;
import com.springboot.microservices.stocktradingapp.domain.User;


@Service
public class RegisterServiceImpl implements RegisterService {



	private static final Logger logger = LoggerFactory.getLogger(StockTradingAppApplication.class);	
	
	
	static Map<String,User> userMap = new HashMap<>();
	

	@Override
	public User save(User user) {

		logger.debug("--Inside RegisterServiceImpl save user --"+user);
		userMap.put(user.getUserId(), user);
		
		return user;
	}


	@Override
	public String getUser(User user) {
		
		logger.debug("--Inside RegisterServiceImpl GetUser --"+user);
		
		   if(userMap.containsKey(user.getUserId())){
				User checkuser = userMap.get(user.getUserId());
				
				
				if(!checkuser.getPassword().equals(user.getPassword())){
					logger.debug("--Password not matching--"+user);
					
					 return "Invalid Password.Please enter correct password.";					
				}
		   }else{
			   logger.debug("--User is not registered--"+user);
			   return "Invalid user.Please register.";
		   }
		
		   
		   return "success";
	}


	
	
}