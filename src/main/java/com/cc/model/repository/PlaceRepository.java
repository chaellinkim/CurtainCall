package com.cc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place,String>{

}
