package com.cc.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.dto.PlaceDto;
import com.cc.model.entity.Place;
import com.cc.model.mapper.PlaceMapper;
import com.cc.model.repository.PlaceRepository;

@Service
public class PlaceService {
	private PlaceRepository placeRepository;
	private PlaceMapper placeMapper;
	
	@Autowired
	public PlaceService(PlaceRepository placeRepository, PlaceMapper placeMapper) {
		super();
		this.placeRepository = placeRepository;
		this.placeMapper = placeMapper;
	}

	public void insert(PlaceDto placeDto) {
		Place place = placeMapper.toEntity(placeDto);
		placeRepository.save(place);
	}
	
	public List<Place> selectAll(){
		return placeRepository.findAll();
	}
	
	public Optional<Place> selectOne(String placeId){
		return placeRepository.findById(placeId);
	}
	
	/*
	 * public List<Place> getUpdatedTabs(int currentTabIndex, int numVisibleTabs) {
	 * int startIndex = currentTabIndex * numVisibleTabs; int endIndex = startIndex
	 * + numVisibleTabs;
	 * 
	 * List<Place> allPlaces = placeRepository.findAll(); if (startIndex >=
	 * allPlaces.size()) { // 범위를 벗어나는 경우 빈 리스트 반환 return Collections.emptyList(); }
	 * 
	 * if (endIndex > allPlaces.size()) { // endIndex가 리스트 크기를 초과하는 경우 endIndex를 리스트
	 * 크기로 조정 endIndex = allPlaces.size(); }
	 * 
	 * // 범위에 해당하는 부분 리스트 반환 return allPlaces.subList(startIndex, endIndex); }
	 */
}
