package com.cc.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Actor;
import com.cc.model.service.ActorService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@RequestMapping("/pay")
	public String actorList() {
		
		return "pay";
	}
	
	@RequestMapping("/review")
	public String review() {
		
		return "review";
	}
	@RequestMapping("/wish")
	public String wish() {
		
		return "wish";
	}
	@RequestMapping("/info")
	public String info() {
		
		return "info";
	}
}
