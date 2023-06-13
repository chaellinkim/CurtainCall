package com.cc.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cc.model.dto.UserPlayDto;
import com.cc.model.service.PlayService;
import com.cc.model.service.UserPlayService;

@Controller
public class PayController {
	private UserPlayService userPlayService;
	
	public PayController(UserPlayService userPlayService) {
		super();
		this.userPlayService = userPlayService;
	}

	@RequestMapping("/pay")
	public String pay() {
		
		return "playpayment";
	}
	
	@RequestMapping(value ="/pay/reservation", method = RequestMethod.POST)
	public String reservation(@RequestBody UserPlayDto userPlayDto) {
		System.out.println(userPlayDto);
		userPlayService.insert(userPlayDto);
		
		return "join";
	}

}
