package com.cc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.ActorPlay;
import com.cc.model.entity.ActorPlayId;

@Repository
public interface ActorPlayRepository extends JpaRepository<ActorPlay, ActorPlayId>{
	List<ActorPlay> findByActor_actorId(long actorId);
}
