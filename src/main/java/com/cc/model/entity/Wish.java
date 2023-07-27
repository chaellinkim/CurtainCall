package com.cc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "WISH")
public class Wish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wish_id; //식별자
	
	@Column(name="USER_ID")
	private int userid; //유저 식별자
	
	@Column(name="PLAY_TITLE")
	private String playtitle; //연극 식별자

	@Column(name="PLAY_POSTER")
	private String playposter;

	@Transient
	private String playId;
	
	public Long getWish_id() {
		return wish_id;
	}

	public void setWish_id(Long wish_id) {
		this.wish_id = wish_id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPlaytitle() {
		return playtitle;
	}

	public void setPlaytitle(String playtitle) {
		this.playtitle = playtitle;
	}

	public String getPlayposter() {
		return playposter;
	}

	public void setPlayposter(String playposter) {
		this.playposter = playposter;
	}

	@Override
	public String toString() {
		return "Wish [wish_id=" + wish_id + ", userid=" + userid + ", playtitle=" + playtitle + ", playposter="
				+ playposter + "]";
	}
	

	
	
}
