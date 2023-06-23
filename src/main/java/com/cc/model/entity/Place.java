package com.cc.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

@Data
@Entity
@Table(name="PLACE")
public class Place {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="place_id")
	private String placeId;
	
	@Column(name="place_name")
	private String placeName;
	
	@Column(name="place_addr")
	private String placeAddr;
	
	@Column(name="place_phone")
	private String placePhone;
	
	@Column(name="place_link")
	private String placeLink;
	
	@Column(name="place_la")
	private BigDecimal la;
	
	@Column(name="place_lo")
	private BigDecimal lo;
	
	@OneToMany(mappedBy="place", fetch = FetchType.LAZY)
	private List<Play> playList = new ArrayList<>();

}
