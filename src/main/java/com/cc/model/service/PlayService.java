package com.cc.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.dto.PlayDto;
import com.cc.model.entity.Play;
import com.cc.model.mapper.PlayMapper;
import com.cc.model.repository.PlayRepository;

@Service
public class PlayService {
	private PlayRepository playRepository;
	private PlayMapper playMapper;
	
	
	@Autowired
	public PlayService(PlayRepository playRepository, PlayMapper playMapper) {
		super();
		this.playRepository = playRepository;
		this.playMapper = playMapper;
	}

	public void insert(PlayDto playDto) {
		Play play = playMapper.toEntity(playDto);
		playRepository.save(play);
	}
	
}
