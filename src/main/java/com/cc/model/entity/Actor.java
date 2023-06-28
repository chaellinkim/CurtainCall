package com.cc.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="ACTOR")
@ToString(exclude = {"commentList","actorPlaylist"}) 
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="actor_id")
	private long actorId;
	
	@Column(name="actor_name")
	private String actorName;
	
	@Column(name="actor_img")
	private String actorImg;
	
	@Column(name="actor_birth")
	private String actorBirth;
	
	@Column(name="actor_sns")
	private String actorSNS;
	
	@JsonIgnore
	@OneToMany(mappedBy = "actor", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)		//EAGER: 처음 가져올 때 매핑된 데이터 가져옴
	private List<ActorComment> commentList;
	
	@JsonIgnore
	@OneToMany(mappedBy = "actor", cascade = CascadeType.REMOVE)
	private List<ActorPlay> actorPlaylist;
}