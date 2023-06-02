package com.cc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.entity.Actor;
import com.cc.model.mapper.PlayMapper;
import com.cc.model.repository.ActorRepository;
import com.cc.model.repository.PlayRepository;

@Service
public class ActorService {

	private ActorRepository actorRepository;
	
	@Autowired
	public ActorService(ActorRepository actorRepository) {
		super();
		this.actorRepository = actorRepository;
	}
	
	public List<Actor> selectAll(){
		return actorRepository.findAll();
	}

	
}
