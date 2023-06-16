package com.cc.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Play;

@Repository
public interface PlayRepository extends JpaRepository<Play,String>{
	Page<Play> findByPlayTitleContaining(String keyword, Pageable pageable);
	List<Play> findByPlayToAfter(LocalDate currentDate);
}
