package com.cc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cc.model.entity.Wish;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long>{
	//찜 목록에서 필요한 거
	/*
	 * 1.영화 제목
	 * 2.영화 포스터
	 */
	

	//넣을 기능
	/*
	 * 1.user_id에 따라 불러오기
	 * 2.쓰레기통 버튼 눌렀을 때 삭제하기
	 */
	
	List<Wish> findByUserid(int user_id);
	
	//repository
    @Transactional
    @Modifying
    @Query("DELETE FROM Wish WHERE PLAY_TITLE=?1")
    int deleteByPlaytitle(String playtitle, int userid);
}
