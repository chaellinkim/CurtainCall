package com.cc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Page<Place> selectAll(Pageable pageable){
		return placeRepository.findAll(pageable);
	}
}
