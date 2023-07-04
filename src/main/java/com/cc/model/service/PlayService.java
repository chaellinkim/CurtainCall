package com.cc.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cc.model.dto.PlayDto;
import com.cc.model.entity.Play;
import com.cc.model.mapper.PlayMapper;
import com.cc.model.mapper.ReviewMapper;
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
	
	public Page<Play> selectAll(Pageable pageable){
		return playRepository.findAll(pageable);
	}
	public Page<Play> selectPossible(Pageable pageable){
		return playRepository.selectPossible(pageable);
	}
	
	public Page<Play> search(String keyword,Pageable pageable){
		Page<Play> plays = playRepository.findByPlayTitleContaining(keyword, pageable);
		return plays;
	}
	public Page<Play> searchPossible(String keyword,Pageable pageable){
		Page<Play> plays = playRepository.searchPossible(keyword, pageable);
		return plays;
	}
	public Optional<Play> selectOne(String playId) {
		Optional<Play> play = playRepository.findById(playId);
		return play;
	}
	public int updateCount(int count, String playId) {
		//System.out.println("call"+playId+" "+count);
		return playRepository.updateCount(count, playId);
	}
	public List<Play> selectBestPlay(){
		return playRepository.findTop4ByOrderByCountDescPlayToDesc();
	}
	
	//playService

	   public List<ReviewMapper> selectTitle(){
	      return playRepository.findAllByOrderByPlayTitleAsc();
	   }
}
