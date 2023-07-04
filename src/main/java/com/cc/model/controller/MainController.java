package com.cc.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.dto.UserPlayDto;
import com.cc.model.entity.Play;
import com.cc.model.service.PlayService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@RequestMapping(value ="/date", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,List<Play>> reservation(@RequestBody String date) {
		ObjectMapper objectMapper = new ObjectMapper();
	    Map<String, String> requestBody = null;
	    try {
	        requestBody = objectMapper.readValue(date, new TypeReference<Map<String, String>>() {});
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    String selectedWeekday = requestBody.get("weekday");
	    String selectedDate = requestBody.get("date");
	    
	    List<Play> list = playService.selectDatePlay(selectedWeekday, selectedDate);
	    
		Map<String,List<Play>> response = new HashMap<>();
		
		response.put("list",list);
		
		return response;
	}
}
