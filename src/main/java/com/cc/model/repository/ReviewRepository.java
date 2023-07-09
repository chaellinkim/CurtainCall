package com.cc.model.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cc.model.entity.Play;
import com.cc.model.entity.Review;

//리뷰 db 만들면 jpa 상속 하기
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
   //bestReview
   List<Review> findTop3ByOrderByCountDesc();
   
   @Transactional
   @Modifying
   @Query("UPDATE REVIEW SET REVIEW_COUNT = REVIEW_COUNT + 1 WHERE REVIEW_ID = ?1")
   int update(long review_id);
   
 //ReviewRepository

   @Query("SELECT R FROM REVIEW R WHERE PLAY_TITLE = ?1 ORDER BY REVIEW_DATE DESC")
   List<Review> findByPlay_title(String play_title);
   
   @Query("SELECT R FROM REVIEW R ORDER BY REVIEW_DATE DESC")
   List<Review> findAllDESC();
   
   @Query("SELECT R FROM REVIEW R WHERE REVIEW_COMMENT LIKE %:keyword% OR PLAY_TITLE LIKE %:keyword%")
   List<Review> searchPossible(@Param("keyword") String keyword);
   
   @Query("SELECT R FROM REVIEW R WHERE USER_ID = ?1 ORDER BY REVIEW_DATE DESC")
	List<Review> findByUser_id(int user_id);

  	@Transactional
	@Modifying
	@Query("UPDATE REVIEW SET REVIEW_IMG = ?2, REVIEW_COMMENT = ?3 WHERE REVIEW_ID = ?1")
	int updateReview(long review_id, byte[] reviewimg, String reviewcomment);
}

