package com.cc.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Play;

@Transactional
@Repository
public interface PlayRepository extends JpaRepository<Play,String>, PlayRepositoryCustom{
	Page<Play> findByPlayTitleContaining(String keyword, Pageable pageable);
	Page<Play> selectPossible(Pageable pageable);
	Page<Play> searchPossible(String keyword, Pageable pageable);	
	
	@Modifying
	@Query("UPDATE Play p SET p.count = :count WHERE p.playId = :playId")
	int updateCount(@Param("count")int count, @Param("playId")String playId);
	
//	@Modifying
//    @Query("UPDATE Play p SET p.count = :count WHERE p.playId = :playId")
//    int updateCount(@Param("playId")String playId, @Param("count")long count);
}