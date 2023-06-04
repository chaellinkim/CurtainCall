package com.cc.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	@RequestMapping("/join")
	public String jogin() {
		
		return "join";
	}
}
