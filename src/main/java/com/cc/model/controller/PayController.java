package com.cc.model.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.dto.UserPlayDto;
import com.cc.model.entity.Play;
import com.cc.model.service.PlayService;
import com.cc.model.service.UserPlayService;

@Controller
public class PayController {
	private UserPlayService userPlayService;
	private PlayService playService;

	public PayController(UserPlayService userPlayService, PlayService playService) {
		super();
		this.userPlayService = userPlayService;
		this.playService = playService;
	}

	@RequestMapping("/pay")
	public String pay() {
		
		return "playpayment";
	}
	
	@RequestMapping(value ="/pay/reservation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> reservation(@RequestBody UserPlayDto userPlayDto) {
		userPlayService.insert(userPlayDto);
//		Optional<Play> play = playService.selectOne(userPlayDto.getPlayId());
//		if(play.get() != null) {
//			int count = play.get().getCount()+1;
//			String playId = userPlayDto.getPlayId();
//			int rowsAffected = playService.updateCount(count, playId);
//			System.out.println(rowsAffected);
//			if (rowsAffected == 0) {
//				System.out.println("실패  ");
//			}
//		}
		
		Map<String,String> response = new HashMap<>();
		String url = "/play";
		response.put("url",url);
		
		return response;
	}

}
