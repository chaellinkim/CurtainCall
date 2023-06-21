package com.cc.model.controller;

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
		
		model1.addAttribute("alist", alist);
		model2.addAttribute("plist", plist);
		model2.addAttribute("comments", comments);
		
		return "actorlist";
	}

	@GetMapping("/actor/{actorId}")
    @ResponseBody
    public List<ActorComment> getCommentsByActorId(@PathVariable("actorId") long actorId) {
        return actorCommentService.getCommentsByActorId(actorId);
    }

	@PostMapping("/actor/comment")
	@ResponseBody
	public List<ActorComment> addComment(@RequestParam("acContent") String acContent, @RequestParam("actorId") long actorId) {		
		ActorComment comment = new ActorComment();
	    
	    Actor actor = new Actor();
	    actor.setActorId(actorId);
	    
	    comment.setActor(actor);
	    comment.setAcContent(acContent);

	    comment.setUserId("ekek");

	    actorCommentService.insert(comment);
	    System.out.println("댓글 내용: " + comment.getAcContent());

	    List<ActorComment> comments = actorCommentService.selectAll();
	    return comments;
	}
	
}
