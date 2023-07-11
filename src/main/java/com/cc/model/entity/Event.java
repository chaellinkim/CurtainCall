package com.cc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="EVENT")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="event_id")
	private long eventId;
	
	@Column(name="event_title")
	private String eventTitle;
	
	@Column(name="event_from")
	private String eventFrom;
	
	@Column(name="event_to")
	private String eventTo;
	
	@Column(name="event_place")
	private String eventPlace;
	
	@Column(name="event_homepage")
	private String eventHompage;
	
	@Column(name="event_content")
	private String eventContent;
	
	@Column(name="event_img")
	private String eventImg;
	
	@Column(name="event_img2")
	private String eventImg2;
	
}
