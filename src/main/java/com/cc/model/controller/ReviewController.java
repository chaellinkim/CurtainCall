package com.cc.model.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Actor;

@Controller
public class ReviewController {
	@RequestMapping("/review")
	public String reviewList(Model model) {
		
		return "review";
	}
}
