package com.cc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.UserPlay;

@Repository
public interface UserPlayRepository extends JpaRepository<UserPlay,String>{
	//예매내역 가져오기
	List<UserPlay> findByUserId(int id);
}
