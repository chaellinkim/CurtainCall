package com.cc.model.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Play;
import com.cc.model.service.PlayService;

@Controller
public class MainController {
	private PlayService playService;
	
	public MainController(PlayService playService) {
		super();
		this.playService = playService;
	}

	@RequestMapping("/")
	public String main(HttpSession session, Model model) {
		
		//로그인 상태 확인 (로그인 된 상태: user_state, 로그인 안 된 상태: null)
		model.addAttribute("user_state", session.getAttribute("user_state")); 
		model.addAttribute("user_id", session.getAttribute("user_id"));
				
		System.out.println((String)session.getAttribute("user_state"));
		
		//예매 top4
		List <Play> bestPlay = playService.selectBestPlay();
		model.addAttribute("bestPlay",bestPlay);
		System.out.println(bestPlay);
				
		return "main";
	}
}
