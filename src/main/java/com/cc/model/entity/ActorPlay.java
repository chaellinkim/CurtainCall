package com.cc.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@IdClass(ActorPlayId.class)
@Table(name="ACTORPLAY")
public class ActorPlay {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="actor_id")
	private Actor actor;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "play_id")
	private Play play;

}
