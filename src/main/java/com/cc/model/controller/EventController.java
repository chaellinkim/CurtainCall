package com.cc.model.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Event;
import com.cc.model.service.EventService;

@Controller
public class EventController {
	EventService eventService;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping("/event")
	public String list(HttpSession session, Model model) {
		List<Event> list = eventService.selectAll();
		model.addAttribute("list", list);
		model.addAttribute("user_state", session.getAttribute("user_state"));
		return "eventlist";
	}
	
	@RequestMapping("/event/detail")
	public String eventDetail(long eventId, Model model) {
		Optional<Event> event = eventService.selectOne(eventId);
		model.addAttribute("event", event);
		return "eventdetail";
	}
}
