package com.cc.model.service;

import org.springframework.stereotype.Service;

import com.cc.model.dto.UserPlayDto;
import com.cc.model.entity.UserPlay;
import com.cc.model.mapper.UserPlayMapper;
import com.cc.model.repository.UserPlayRepository;

@Service
public class UserPlayService {
	private UserPlayRepository userPlayRepository;
	private UserPlayMapper userPlayMapper;
	
	
	public UserPlayService(UserPlayRepository userPlayRepository, UserPlayMapper userPlayMapper) {
		super();
		this.userPlayRepository = userPlayRepository;
		this.userPlayMapper = userPlayMapper;
	}

	public void insert(UserPlayDto userPlayDto) {
		UserPlay userPlay = userPlayMapper.toEntity(userPlayDto);
		System.out.println(userPlay);
		userPlayRepository.save(userPlay);
	}
}
