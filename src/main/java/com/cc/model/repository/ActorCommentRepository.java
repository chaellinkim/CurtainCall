package com.cc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.ActorComment;

@Repository
public interface ActorCommentRepository extends JpaRepository<ActorComment, Long> {
	List<ActorComment> findByActor_actorId(long actorId);
}
