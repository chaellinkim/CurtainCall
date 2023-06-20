package com.cc.model.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Actor;
import com.cc.model.service.ActorService;

@Controller

public class MyPageController {
	
	@RequestMapping("/mypage")
	public String Mypage(HttpSession session, Model model) {
		model.addAttribute("user_state", session.getAttribute("user_state")); 
		return "mypage";
	}
}
