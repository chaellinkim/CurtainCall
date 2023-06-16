package com.cc.model.repository;

import java.util.List;

import com.cc.model.entity.Play;

public interface PlayRepositoryCustom {
	List<Play> selectPossible();
}
