package com.cc.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PLACE")
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="place_id")
	private int placeId;
	
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
	
	@Column(name="place_nickname")
	private String placeNickname;

}
