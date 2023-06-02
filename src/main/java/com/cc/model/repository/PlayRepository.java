package com.cc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Play;

@Repository
public interface PlayRepository extends JpaRepository<Play,Long>{
	
}
