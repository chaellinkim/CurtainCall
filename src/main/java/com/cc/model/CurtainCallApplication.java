package com.cc.model;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class CurtainCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurtainCallApplication.class, args);
	}

	@RequestMapping("/")
	public String main(HttpSession session, Model model) {
		
		//�α��� ���� Ȯ�� (�α��� �� ����: user_state, �α��� �� �� ����: null)
		model.addAttribute("user_state", session.getAttribute("user_state")); 
		model.addAttribute("user_id", session.getAttribute("user_id"));
				
		System.out.println((String)session.getAttribute("user_state"));
				
		return "main";
	}
}

