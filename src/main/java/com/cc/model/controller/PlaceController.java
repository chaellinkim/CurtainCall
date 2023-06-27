package com.cc.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	public String list(Model model, @PageableDefault(page=0, size=18, sort="placeName", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Place> listPlace = placeService.selectAll(pageable);
		List<List<Play>> listPlay = new ArrayList<>();
	
		for (Place place : listPlace) {
			List<Play> Play = place.getPlayList();
			if (Play.size() != 0) {
				listPlay.add(Play);
			} else {
				listPlay.add(null);
			}
		}
		int nowPage = listPlace.getPageable().getPageNumber()+1;
		int startPage = 1;
		int endPage = listPlace.getTotalPages();

		model.addAttribute("list", listPlace);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("listPlay", listPlay);
		
		return "placelist";
	}
}
