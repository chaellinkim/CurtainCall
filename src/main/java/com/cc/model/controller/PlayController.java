package com.cc.model.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.cc.model.entity.Actor;
import com.cc.model.entity.ActorPlay;
import com.cc.model.entity.Place;
import com.cc.model.entity.Play;
import com.cc.model.entity.Review;
import com.cc.model.entity.User;
import com.cc.model.repository.UserRepository;
import com.cc.model.service.PlaceService;
import com.cc.model.service.PlayService;
import com.cc.model.service.ReviewService;

@Controller
public class PlayController {
	private PlayService playService;
	private PlaceService placeService;
	private ReviewService reviewService;

	public PlayController(PlayService playService, PlaceService placeService, ReviewService reviewService) {
		super();
		this.playService = playService;
		this.placeService = placeService;
		this.reviewService = reviewService;
	}

	@Autowired
	private UserRepository userRep;

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
	public String playList(Model model, HttpSession session,
			@PageableDefault(page = 0, size = 12, sort = "playId", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Play> list = playService.selectPossible(pageable);

		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = 1;
		int endPage = list.getTotalPages();

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("user_state", session.getAttribute("user_state"));

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
	@RequestMapping(value = "/play/search", method = RequestMethod.GET)
	public String playList(String keyword, Model model,
			@PageableDefault(page = 0, size = 12, sort = "playTitle", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Play> list = playService.searchPossible(keyword, pageable);
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = 1;
		int endPage = list.getTotalPages();

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "playlist";
	}

	@RequestMapping(value = "/play/detail", method = RequestMethod.GET)
	public String playdetail(@RequestParam("playId") String playId, Model model,HttpSession session) {
		System.out.println(playId);
		Optional<Play> play = playService.selectOne(playId);
		Optional<Place> place = placeService.selectOne(play.get().getPlaceId());
		List<ActorPlay> actorPlay = play.get().getActorPlayList();
		System.out.println(play);
		model.addAttribute("play", play);
		List<User> user = userRep.findAll();

		// 관람후기
		String playtitle = play.orElse(new Play()).getPlayTitle();
		List<Review> review = reviewService.selectOne(playtitle);
		List<Actor> actorList = new ArrayList<>();
		for (ActorPlay a : actorPlay) {
			actorList.add(a.getActor());
		}
		for (Review rev : review) {
			byte[] imageData = rev.getReview_img();
			String encodedImageData = Base64.encodeBase64String(imageData);
			rev.setEncodedImage(encodedImageData);

			int UserId = rev.getUser_id();

			for (User u : user) {
				if (UserId == u.getUser_id()) {
					rev.setUserName(u.getUser_namemasking());
				}
			}
		}
		model.addAttribute("play", play);
		model.addAttribute("place", place);
		model.addAttribute("actor", actorList);
		model.addAttribute("review", review);
		model.addAttribute("user_state", session.getAttribute("user_state"));
		return "playdetail";
	}

}
