package com.cc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.entity.Actor;
import com.cc.model.repository.ActorRepository;

@Service
public class ActorService {
	private ActorRepository actorRepository;
	
	@Autowired	//출연진 리스트
	public ActorService(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}
	
	public List<Actor> selectAll(){
		return actorRepository.findAll();
	}
	
	public Actor getActorById(long actorId) {		 
		return actorRepository.findById(actorId).orElse(null);
	}
}
