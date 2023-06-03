package com.cc.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cc.model.entity.Play;
import com.cc.model.service.PlayService;

@Controller
public class PlayController {
	@Autowired
	private PlayService playService;

	@RequestMapping("/play")
	public String playList(Model model,
			@PageableDefault(page = 0, size = 12, sort = "playId", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Play> list = playService.selectAll(pageable);

		int nowPage = list.getPageable().getPageNumber()+1;
		int startPage = 1;
		int endPage = list.getTotalPages();

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "playlist";

	}

}
