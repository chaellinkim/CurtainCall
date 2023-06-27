package com.cc.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cc.model.entity.Actor;
import com.cc.model.entity.ActorComment;
import com.cc.model.repository.ActorCommentRepository;
import com.cc.model.repository.ActorRepository;

@Service
public class ActorCommentService {
	private ActorCommentRepository actorCommentRepository;
	private ActorRepository actorRepository;

	@Autowired
	public ActorCommentService(ActorCommentRepository actorCommentRepository, ActorRepository actorRepository) {
		this.actorCommentRepository = actorCommentRepository;
		this.actorRepository = actorRepository;
	}

	//댓글 출력
	public List<ActorComment> selectAll() {
		return actorCommentRepository.findAll(Sort.by(Sort.Direction.DESC, "created"));
	}
	
	//댓글 저장
	public void insert(ActorComment comment, long actorId, String loginId) {
		Optional<Actor> actor = actorRepository.findById(actorId);
		comment.setActor(actor.orElse(null));
		comment.setUserLoginid(loginId);
		
		actorCommentRepository.save(comment);
	}
	
	//출연진 아이디 검색
	public List<ActorComment> getCommentsByActorId(long actorId) {
		return actorCommentRepository.findByActor_actorId(actorId);
    }

	//댓글 삭제
	public void delete(long commentId) {
		actorCommentRepository.deleteById(commentId);
	}
}
