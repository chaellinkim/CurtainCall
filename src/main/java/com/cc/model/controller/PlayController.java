package com.cc.model.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.entity.Actor;
import com.cc.model.entity.ActorPlay;
import com.cc.model.entity.Place;
import com.cc.model.entity.Play;
import com.cc.model.entity.QPlay;
import com.cc.model.entity.Review;
import com.cc.model.entity.User;
import com.cc.model.entity.Wish;
import com.cc.model.repository.UserRepository;
import com.cc.model.service.PlaceService;
import com.cc.model.service.PlayService;
import com.cc.model.service.ReviewService;
import com.cc.model.service.WishService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Controller
public class PlayController {
	private PlayService playService;
	private PlaceService placeService;
	private ReviewService reviewService;
private WishService wishSvc;
	
	public PlayController(PlayService playService, PlaceService placeService, ReviewService reviewService,WishService wishSvc) {
		super();
		this.playService = playService;
		this.placeService = placeService;
		this.reviewService = reviewService;
		this.wishSvc = wishSvc;
	}

	@Autowired
	private UserRepository userRep;

	@Autowired
	private EntityManager entityManager;

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
			@PageableDefault(page = 0, size = 12) Pageable pageable, @RequestParam(name = "sort", required = false) String sortOption) {
		

		BooleanBuilder builder = new BooleanBuilder();
		    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		    QPlay qPlay = QPlay.play;
		    
		    List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
		    
		    if (sortOption == null || sortOption.equals("random")) {
		        Sort sort = Sort.by(qPlay.playId.getMetadata().getName()).descending();
		        orderSpecifiers.add(qPlay.playId.asc());
		    } else if (sortOption.equals("heart")) {
		        builder.and(qPlay.count.isNotNull());
		        orderSpecifiers.add(qPlay.count.desc());
		    } else if (sortOption.equals("name")) {
		        builder.and(qPlay.playTo.isNotNull());
		        orderSpecifiers.add(qPlay.playTitle.asc());
		    } else if (sortOption.equals("recent")) {
		        builder.and(qPlay.playTo.isNotNull());
		        orderSpecifiers.add(qPlay.playTo.asc());
		    }

		    // 필터 조건을 적용하여 쿼리 실행
		    List<Play> list = queryFactory.selectFrom(qPlay)
		            .where(builder)
		            .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]))
		            .offset(pageable.getOffset())
		            .limit(pageable.getPageSize())
		            .fetch();

	    long totalCount = queryFactory.selectFrom(qPlay).where(builder).fetchCount();

	    Page<Play> playPage = new PageImpl<>(list, pageable, totalCount);
		
		int nowPage = playPage.getPageable().getPageNumber() + 1;
		int startPage = 1;
		int endPage = playPage.getTotalPages();

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

				/* 찜 목록 탭 */
		// 로그인한 유저의 찜 목록을 가져옴
		Integer userid = (Integer) session.getAttribute("user_id");

		if (userid != null) {
			List<Wish> playwish = wishSvc.findAllByUserid(userid);

			model.addAttribute("playwish", playwish);
			model.addAttribute("playTitle", play.orElse(new Play()).getPlayTitle());
			model.addAttribute("playPoster", play.orElse(new Play()).getPlayPoster());
		}
		
		model.addAttribute("play", play);
		model.addAttribute("place", place);
		model.addAttribute("actor", actorList);
		model.addAttribute("review", review);
		model.addAttribute("user_state", session.getAttribute("user_state"));
		return "playdetail";
	}

		// 찜 삭제
	@ResponseBody
	@PostMapping("/play/detail/delete")
	public String deleteWish(@RequestParam("playtitle") String playTitle, HttpSession session) {

		Integer userid = (Integer) session.getAttribute("user_id");

		if (wishSvc.deleteWish(playTitle, userid) > 0) {
			playService.discountCount(playTitle);
		} else {
			return "false";
		}

		return "true";
	}

	// 찜 추가
	@ResponseBody
	@PostMapping("/play/detail/update")
	public String updateWish(@RequestParam("playtitle") String playTitle, @RequestParam("playposter") String playPoster,
			HttpSession session) {

		Integer userid = (Integer) session.getAttribute("user_id");

		if (wishSvc.insertWish(playTitle, userid, playPoster)) {
			playService.updateCount(playTitle);
		} else {
			return "false";
		}

		return "true";
	}

}
