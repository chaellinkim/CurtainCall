package com.cc.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.entity.Event;

@Transactional
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
