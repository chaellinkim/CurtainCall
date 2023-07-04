package com.cc.model.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.entity.Event;
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
	
		int currentTabIndex = 0;
		int numVisibleTabs = 15;
		
		model.addAttribute("list", listPlace);
		model.addAttribute("listPlay", listPlay);
		model.addAttribute("currentTabIndex", currentTabIndex);
		model.addAttribute("numVisibleTabs", numVisibleTabs);
		model.addAttribute("user_state", session.getAttribute("user_state"));
		return "placelist";
	}
	
	/*
	 * @RequestMapping(value = "/nextButtonClick", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public List<Place> nextButtonClick(@RequestParam int
	 * currentTabIndex) { currentTabIndex++; // 다음 탭 인덱스 계산
	 * 
	 * List<Place> tabData = placeService.getUpdatedTabs(currentTabIndex,
	 * numVisibleTabs); // 업데이트된 탭 데이터 반환 return tabData; }
	 */
	
}
