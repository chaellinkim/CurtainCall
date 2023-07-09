package com.cc.model.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Play;
import com.cc.model.mapper.ReviewMapper;

@Transactional
@Repository
public interface PlayRepository extends JpaRepository<Play,String>, PlayRepositoryCustom{
	Page<Play> findByPlayTitleContaining(String keyword, Pageable pageable);
	Page<Play> selectPossible(Pageable pageable);
	Page<Play> searchPossible(String keyword, Pageable pageable);
	
	//Optional<Play> findTop4ByOrderByCountDesc();
	List<Play> findTop4ByOrderByCountDescPlayToDesc();
	
//	@Modifying
//	@Query("UPDATE Play p SET p.count = :count WHERE p.playId = :playId")
//	int updateCount(@Param("count")int count, @Param("playId")String playId);
	
	@Modifying
	@Query("UPDATE Play p SET p.count = ?1 WHERE p.playId = ?2")
	int updateCount(int count, String playId);
	
	@Query("SELECT p FROM Play p WHERE p.day = ?1 AND p.playFrom <= ?2 AND p.playTo >= ?2")
    List<Play> findPlaysByDayAndDateBetween(String weekday, String date);
  
	//playrepository
	List<ReviewMapper> findAllByOrderByPlayTitleAsc();
	
	Play findByPlayTitle(String title);
}
