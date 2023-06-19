package com.cc.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cc.model.entity.Play;

public interface PlayRepositoryCustom {
	Page<Play> selectPossible(Pageable Pageable);
	Page<Play> searchPossible(String keyword, Pageable pageable);
}
