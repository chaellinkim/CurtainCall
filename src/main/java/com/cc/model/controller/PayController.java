package com.cc.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayController {
	@RequestMapping("/play/pay")
	public String pay() {
		
		return "playpayment";
	}

}
