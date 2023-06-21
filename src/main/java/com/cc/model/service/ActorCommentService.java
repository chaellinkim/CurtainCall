package com.cc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.entity.ActorComment;
import com.cc.model.repository.ActorCommentRepository;

@Service
public class ActorCommentService {
	private ActorCommentRepository actorCommentRepository;
	
	@Autowired
	public ActorCommentService(ActorCommentRepository actorCommentRepository) {
		this.actorCommentRepository = actorCommentRepository;
	}

	//댓글 출력
	public List<ActorComment> selectAll() {
		return actorCommentRepository.findAll();
	}
	
	//댓글 저장
	public void insert(ActorComment comment) {
		actorCommentRepository.save(comment);
	}
	
	//출연진 아이디 검색
	public List<ActorComment> getCommentsByActorId(long actorId) {
		return actorCommentRepository.findByActor_actorId(actorId);
    }

}
