package com.cc.model.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.dto.ActorCommentDto;
import com.cc.model.entity.Actor;
import com.cc.model.entity.ActorComment;
import com.cc.model.entity.ActorPlay;
import com.cc.model.entity.User;
import com.cc.model.service.ActorCommentService;
import com.cc.model.service.ActorPlayService;
import com.cc.model.service.ActorService;
import com.cc.model.service.UserService;

@Controller
public class ActorController {
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ActorCommentService actorCommentService;
	
	@Autowired
	private ActorPlayService actorPlayService;
	
	@RequestMapping("/actor")
	public String actorList(HttpSession session, Model model) {
		List<Actor> alist = actorService.selectAll();
		List<ActorPlay> plist = actorPlayService.selectAll();
		List<ActorComment> comments = actorCommentService.selectAll();
		List<Boolean> commentExists = new ArrayList<>();
		for (Actor actor : alist) {
			boolean hasComment = comments.stream()
					.anyMatch(comment -> comment.getActor().getActorId() == actor.getActorId());
		    commentExists.add(hasComment);
		}

		model.addAttribute("alist", alist);
		model.addAttribute("plist", plist);
		model.addAttribute("comments", comments);
		model.addAttribute("commentExists", commentExists);
		model.addAttribute("user_state", session.getAttribute("user_state")); 
		
		return "actorlist";
	}

	@PostMapping("/actor/comment")
	public String insertComment(HttpSession session, @RequestParam("content") String content, @RequestParam("actorId") long actorId, Model model) {	
		Actor actor = actorService.getActorById(actorId);
		
	    ActorComment newComment = new ActorComment();
	    newComment.setContent(content);
		newComment.setActor(actor);
		newComment.setCreated(LocalDateTime.now());
		
		String userLoginid = (String)session.getAttribute("userLoginid");		
		newComment.setUserLoginid(userLoginid);
		session.setAttribute("userLoginid", userLoginid);
		
		actorCommentService.insert(newComment, actorId, userLoginid);
		model.addAttribute("newComment", newComment);
		
		return "redirect:/actor?actorId=" + actorId;
	}

	/* DELETE */
	@DeleteMapping("/actor/comment/{commentId}")
	public Map<String, String> deleteComment(@PathVariable long commentId) {
		Map<String,String> response = new HashMap<>();
		String url = "/actor";
        response.put("url",url);
		actorCommentService.delete(commentId);
		return response;
	}
	/*
	@DeleteMapping("/actor/comment/{commentId}")
	public long deleteComment(@PathVariable long commentId) {
		long result;
		Optional<ActorComment> comment = actorCommentService.findById(commentId);
		if(comment.isEmpty()) {
			System.out.println("댓글을 못불러옴");
			result = 0;
			return result;
		}else {
			actorCommentService.delete(commentId);
			return commentId;
		}
	}
	*/

	 
	
}