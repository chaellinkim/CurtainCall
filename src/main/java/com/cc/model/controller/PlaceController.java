package com.cc.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class PlaceController {
	@RequestMapping("/map")
	public String list(Model model){
		
		return "map_google";
	}
}
