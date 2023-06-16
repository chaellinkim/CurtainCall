package com.cc.model.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cc.model.entity.Play;
import com.cc.model.service.PlayService;

@Controller
public class PlayController {
	private PlayService playService;

	public PlayController(PlayService playService) {
		super();
		this.playService = playService;
	}

//	@RequestMapping("/play")
//	public String playList(Model model,
//			@PageableDefault(page = 0, size = 12, sort = "playId", direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<Play> list = playService.selectAll(pageable);
//
//		int nowPage = list.getPageable().getPageNumber()+1;
//		int startPage = 1;
//		int endPage = list.getTotalPages();
//
//		model.addAttribute("list", list);
//		model.addAttribute("nowPage", nowPage);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("endPage", endPage);
//
//		return "playlist";
//
//	}

	@RequestMapping("/play")
	public String playList(Model model,
			@PageableDefault(page = 0, size = 12, sort = "playId", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Play> list = playService.selectPossible(pageable);

		int nowPage = list.getPageable().getPageNumber()+1;
		int startPage = 1;
		int endPage = list.getTotalPages();

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "playlist";

	}
	
//	@RequestMapping(value="/play/search", method=RequestMethod.GET)
//	public String playList(String keyword, Model model, @PageableDefault(page=0, size=12, sort="playId", direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<Play> list = playService.search(keyword, pageable);
//		int nowPage = list.getPageable().getPageNumber()+1;
//		int startPage = 1;
//		int endPage = list.getTotalPages();
//
//		model.addAttribute("list", list);
//		model.addAttribute("nowPage", nowPage);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("endPage", endPage);
//		
//		return "playlist";
//	}
	@RequestMapping(value="/play/search", method=RequestMethod.GET)
	public String playList(String keyword, Model model, @PageableDefault(page=0, size=12, sort="playId", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Play> list = playService.searchPossible(keyword, pageable);
		int nowPage = list.getPageable().getPageNumber()+1;
		int startPage = 1;
		int endPage = list.getTotalPages();

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "playlist";
	}
	
	
	@RequestMapping(value="/play/detail", method=RequestMethod.GET)
	public String playdetail(String playId, Model model) {
		System.out.println(playId);
		Optional<Play> play = playService.selectOne(playId);
		System.out.println(play);
		model.addAttribute("play",play);
		
		return "playdetail";

	}

}
