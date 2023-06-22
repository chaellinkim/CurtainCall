package com.cc.model.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.entity.Actor;
import com.cc.model.entity.ActorComment;
import com.cc.model.entity.ActorPlay;
import com.cc.model.service.ActorCommentService;
import com.cc.model.service.ActorPlayService;
import com.cc.model.service.ActorService;

@Controller
public class ActorController {
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ActorCommentService actorCommentService;
	
	@Autowired
	private ActorPlayService actorPlayService;
	
	@RequestMapping("/actor")
	public String actorList(Model model1, Model model2, Model model3) {
		List<Actor> alist = actorService.selectAll();
		List<ActorPlay> plist = actorPlayService.selectAll();
		List<ActorComment> comments = actorCommentService.selectAll();
		List<Boolean> commentExists = new ArrayList<>();
		for (Actor actor : alist) {
	           boolean hasComment = comments.stream()
	                   .anyMatch(comment -> comment.getActor().getActorId() == actor.getActorId());
	           commentExists.add(hasComment);
	       }

		model1.addAttribute("alist", alist);
		model2.addAttribute("plist", plist);
		model3.addAttribute("comments", comments);
		model3.addAttribute("commentExists", commentExists);
		
		return "actorlist";
	}

	@PostMapping("/actor/comment")
	public String insertComment(@RequestParam("content") String content, @RequestParam("actorId") long actorId, Model model) {	
		Actor actor = actorService.getActorById(actorId);
	    //actor.setActorId(actorId);
	    
	    ActorComment newComment = new ActorComment();
	    newComment.setContent(content);
		newComment.setActor(actor);
		newComment.setCreated(LocalDateTime.now());
		newComment.setUserId("ekgp");
		
		actorCommentService.insert(newComment, actorId);
		model.addAttribute("newComment", newComment);
		
		return "redirect:/actor?actorId=" + actorId;
	}

	/* DELETE */
	
}
