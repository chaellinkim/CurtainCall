package com.cc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.UserPlay;

@Repository
public interface UserPlayRepository extends JpaRepository<UserPlay,String>{
	
}
