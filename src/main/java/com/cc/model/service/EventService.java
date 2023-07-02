package com.cc.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.entity.Event;
import com.cc.model.repository.EventRepository;

@Service
public class EventService {
	private EventRepository eventRepository;
	
	@Autowired
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	public List<Event> selectAll() {
		return eventRepository.findAll();
	}
	
	public Optional<Event> selectOne(long eventId) {
		Optional<Event> event = eventRepository.findById(eventId);
		return event;
	}
}
