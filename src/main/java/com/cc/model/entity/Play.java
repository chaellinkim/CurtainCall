package com.cc.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.ToString;

@Data
@Entity(name="Play")
@Table(name="PLAY")
@ToString(exclude = "place") 
@JsonSerialize
public class Play {
	@Id
	@Column(name="play_id")
	private String playId;
	
	@Column(name="play_title")
	private String playTitle;
	
	@Column(name="play_price")
	private String playPrice;
	
	@Column(name="play_from")
	private String playFrom;
	
	@Column(name="play_to")
	private String playTo;
	
	@Column(name="play_time")
	private String playTime;
	
	@Column(name="play_age")
	private String playAge;
	
	@Column(name="play_poster")
	private String playPoster;
	
	@Column(name="place_id")
	private String placeId;
	
	@Column(name="count")
	private int count;
	
	@Column(name="price")
	private String price;
	
	@Column(name="day")
	private String day;
	
	@Column(name="time")
	private String time;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="place_id", insertable = false, updatable = false)
	private Place place;
  
	@JsonIgnore
	@OneToMany(mappedBy = "play", cascade = CascadeType.REMOVE)
	private List<ActorPlay> actorPlayList;
  
	
}