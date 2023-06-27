package com.cc.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Place;
import com.cc.model.entity.Play;
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
	public String list(HttpSession session, Model model) {
		List<Place> listPlace = placeService.selectAll();
		
		List<List<Play>> listPlay = new ArrayList<>();
	
		for (Place place : listPlace) {
			List<Play> Play = place.getPlayList();
			if (Play.size() != 0) {
				listPlay.add(Play);
			} else {
				listPlay.add(null);
			}
		}
		/*
		Map<String, List<Play>> playMap = new HashMap<>();

		  for (Place place : listPlace) {
		    List<Play> playList = place.getPlayList();
		    playMap.put(place.getPlaceName(), playList);
		  }
		*/
		model.addAttribute("list", listPlace);
		model.addAttribute("listPlay", listPlay); 
			/* model.addAttribute("playMap", playMap); */
		model.addAttribute("user_state", session.getAttribute("user_state"));
		return "placelist";
	}
}
