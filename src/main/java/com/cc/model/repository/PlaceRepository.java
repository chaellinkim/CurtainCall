package com.cc.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place,String>{
	Page<Place> findAll(Pageable pageable);
	
	//장소 이름 가져오기 위함
	List<Place> findByPlaceId(String placeId);
}
