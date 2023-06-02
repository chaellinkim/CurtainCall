package com.cc.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import lombok.Data;

@Data
@Entity
@Table(name="PLAY")
public class Play {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int play_id;
	private String play_title;
	private String play_price;
	private String play_from;
	private String play_to;
	private String play_time;
	private String play_age;
	private String play_poster;
	private String place_id;
}
