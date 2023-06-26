package com.cc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.entity.Actor;
import com.cc.model.entity.ActorPlay;
import com.cc.model.repository.ActorPlayRepository;

@Service
public class ActorPlayService {
	private ActorPlayRepository actorPlayRepository;
	
	@Autowired
	public ActorPlayService(ActorPlayRepository actorPlayRepository) {
		this.actorPlayRepository = actorPlayRepository;
	}
	
	public List<ActorPlay> selectAll(){
		return actorPlayRepository.findAll();
	}
	
	public List<ActorPlay> findByActorId(long actorId) {
		return actorPlayRepository.findByActor_actorId(actorId);
	}
}
