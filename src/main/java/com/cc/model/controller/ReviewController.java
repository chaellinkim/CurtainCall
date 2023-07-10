package com.cc.model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cc.model.entity.Place;
import com.cc.model.entity.Play;
import com.cc.model.entity.Review;
import com.cc.model.entity.User;
import com.cc.model.mapper.ReviewMapper;
import com.cc.model.repository.ReviewRepository;
import com.cc.model.repository.UserRepository;
import com.cc.model.service.PlayService;
import com.cc.model.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	ReviewService reviewSvc;

	@Autowired
	ReviewRepository reviewRep;

	private PlayService playService;

	@Autowired
	public ReviewController(PlayService playService) {
		super();
		this.playService = playService;
	}

	@Autowired
	private UserRepository userRep;

	@RequestMapping("/review")
	public String reviewList(HttpSession session, Model model) {

		List<Review> review = reviewRep.findAllDESC();
		List<User> user = userRep.findAll();
		List<Play> play = new ArrayList<Play>();

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
			/* play.add(playService.selectPlay(rev.getPlay_title())); */
			/*
			 * System.out.println("겟플레이타이틀이다다ㅏ다다: " + rev.getPlay_title());
			 * System.out.println("플레이이이: " + playService.selectPlay(rev.getPlay_title()));
			 */

		}

		List<ReviewMapper> title = playService.selectTitle();
		List<String> playtitle = new ArrayList<String>();

		for (ReviewMapper t : title) {
			playtitle.add(t.getPlayTitle());

		}

		model.addAttribute("play", play);
		model.addAttribute("review", review);
		model.addAttribute("playtitle", playtitle);
		model.addAttribute("user_state", session.getAttribute("user_state"));

		// 리뷰 top3
		List<Review> bestReview = reviewSvc.selectBestReview();

		for (Review rev : bestReview) {
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
		model.addAttribute("bestReview", bestReview);
		return "review";
	}

	@PostMapping("/submit-review")
	public String reviewInsert(HttpSession session, String category, String content,
			@RequestParam(name = "img", required = false) MultipartFile img) throws IllegalStateException, IOException {

		Integer userid = (Integer) session.getAttribute("user_id");

		reviewSvc.reviewInsert(userid, category, content, img);

		return "redirect:/review";
	}

	@ResponseBody
	@PostMapping("/review/likecount")
	public String likeCount(@RequestParam("review_id") String review_id) {

		reviewSvc.countUpdate(review_id);

		return "true";
	}

	/*
	 * //리뷰 검색
	 * 
	 * @RequestMapping(value = "/review/search", method = RequestMethod.GET) public
	 * String reviewSearch(HttpSession session, String keyword, Model model) {
	 * List<Review> searchResults = reviewSvc.searchReviews(keyword);
	 * List<ReviewMapper> title = playService.selectTitle(); List<String> playtitle
	 * = new ArrayList<String>();
	 * 
	 * for(ReviewMapper t:title) { playtitle.add(t.getPlayTitle()); }
	 * 
	 * List<Review> review = reviewRep.findAllDESC(); List<User> user =
	 * userRep.findAll();
	 * 
	 * for (Review rev : review) { byte[] imageData = rev.getReview_img(); String
	 * encodedImageData = Base64.encodeBase64String(imageData);
	 * rev.setEncodedImage(encodedImageData);
	 * 
	 * int UserId =rev.getUser_id();
	 * 
	 * for(User u: user) { if(UserId == u.getUser_id()) {
	 * rev.setUserName(u.getUser_namemasking()); } } }
	 * 
	 * List <Review> bestReview = reviewSvc.selectBestReview();
	 * 
	 * for (Review rev : bestReview) { byte[] imageData = rev.getReview_img();
	 * String encodedImageData = Base64.encodeBase64String(imageData);
	 * rev.setEncodedImage(encodedImageData);
	 * 
	 * int UserId =rev.getUser_id();
	 * 
	 * for(User u: user) { if(UserId == u.getUser_id()) {
	 * rev.setUserName(u.getUser_namemasking()); } } }
	 * model.addAttribute("bestReview",bestReview); model.addAttribute("playtitle",
	 * playtitle); model.addAttribute("user_state",
	 * session.getAttribute("user_state")); model.addAttribute("review",
	 * searchResults); System.out.println("검색결과앙ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ: " +
	 * searchResults);
	 * 
	 * return "review"; }
	 */

}