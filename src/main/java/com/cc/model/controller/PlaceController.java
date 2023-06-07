package com.cc.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Place;
import com.cc.model.service.PlaceService;

@Controller
public class PlaceController {
	PlaceService placeService;
	
	@Autowired
	public PlaceController(PlaceService placeService) {
		super();
		this.placeService = placeService;
	}

	@RequestMapping("/place")
	public String list(Model model){
		List<Place> list = placeService.selectAll();
		model.addAttribute("list",list);
		return "placelist";
	}
}
