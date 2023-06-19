package com.cc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="USERPLAY")
public class UserPlay {
	@Id
	@Column(name="reservation_number")
	private String merchantUid;
	@Column(name="user_id")
	private long userId;
	@Column(name="play_id")
	private String playId;
	@Column(name="play_title")
	private String playTitle;

}
