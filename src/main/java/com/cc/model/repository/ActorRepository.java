package com.cc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long>{

}
