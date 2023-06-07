package com.cc.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Play;

@Repository
public interface PlayRepository extends JpaRepository<Play,Long>{
	Page<Play> findByPlayTitleContaining(String keyword, Pageable pageable);
}
