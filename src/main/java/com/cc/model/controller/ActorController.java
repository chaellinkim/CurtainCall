package com.cc.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Actor;
import com.cc.model.service.ActorService;

@Controller
public class ActorController {
	@Autowired
	private ActorService actorService;
	
	@RequestMapping("/actor")
	public String actorList(Model model) {
		List<Actor> list = actorService.selectAll();
		
		model.addAttribute("list",list);
		System.out.println("------");
		System.out.println(list);
		return "actorlist";
	}
}
