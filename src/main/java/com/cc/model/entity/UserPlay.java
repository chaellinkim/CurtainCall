package com.cc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="USERPLAY")
public class UserPlay {
	@Id
	@Column(name="reservation_number")
	private String merchantUid;
	@Column(name="user_id")
	private int userId;
	@Column(name="play_id")
	private String playId;
	@Column(name="play_title")
	private String playTitle;
	@Column(name="price")
	private String price;
	@Column(name="date")
	private String date;
	@Column(name="time")
	private String time;
	@Column(name="paymentdate")
	private String paymentDate;
	
	//연극 포스터, 연극 장소
	@Transient
	private String playPoster;
	@Transient
	private String playPlace;
	
}
