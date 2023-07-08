package com.cc.model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
      
      for (Review rev : review) {
           byte[] imageData = rev.getReview_img();
           String encodedImageData = Base64.encodeBase64String(imageData);
           rev.setEncodedImage(encodedImageData);
           
           int UserId =rev.getUser_id();
           
           for(User u: user) {
              if(UserId == u.getUser_id()) {
                 rev.setUserName(u.getUser_namemasking());
              }
           }
       }

      List<ReviewMapper> title = playService.selectTitle();
      List<String> playtitle = new ArrayList<String>();
      
      for(ReviewMapper t:title) {
         playtitle.add(t.getPlayTitle());
      }

      model.addAttribute("review", review);
      model.addAttribute("playtitle", playtitle);
      model.addAttribute("user_state", session.getAttribute("user_state"));
      
    //리뷰 top3
    		List <Review> bestReview = reviewSvc.selectBestReview();
    	      
    	      for (Review rev : bestReview) {
    	           byte[] imageData = rev.getReview_img();
    	           String encodedImageData = Base64.encodeBase64String(imageData);
    	           rev.setEncodedImage(encodedImageData);
    	           
    	           int UserId =rev.getUser_id();
    	           
    	           for(User u: user) {
    	              if(UserId == u.getUser_id()) {
    	                 rev.setUserName(u.getUser_namemasking());
    	              }
    	           }
    	       }
    		model.addAttribute("bestReview",bestReview);
      return "review";
   }
   
   
   @PostMapping("/submit-review") 
    public String reviewInsert(HttpSession session, String category, String content, @RequestParam(name = "img", required = false) MultipartFile img) throws IllegalStateException, IOException {
      
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
    
}