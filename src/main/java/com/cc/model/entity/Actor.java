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
@Table(name="ACTOR")
public class Actor {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private int actorId;

    @Column(name="actor_name")
    private String actorName;

    @Column(name="actor_img")
    private String actorImg;

    @Column(name="actor_birth")
    private String actorBirth;

    @Column(name="actor_sns")
    private String actorSNS;

    @Column(name="play_id")
    private int playId;

}
