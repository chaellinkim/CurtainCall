package com.cc.model;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@SpringBootApplication
public class CurtainCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurtainCallApplication.class, args);
	}

	@RequestMapping("/")
	public String main() {
		
		//로그인 상태 확인 (로그인 된 상태: user_state, 로그인 안 된 상태: null)
		model.addAttribute("user_state", session.getAttribute("user_state")); 
		model.addAttribute("user_id", session.getAttribute("user_id"));
		
		System.out.println((String)session.getAttribute("user_state"));
		
		return "main";
	}
}

