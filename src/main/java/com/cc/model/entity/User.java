package com.cc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id; //식별자
	
	//아이디
	@Column(name = "USER_LOGINID")
	private String userLoginid;
	private String user_logincypher; //암복호화
	
	//비밀번호
	@Column(name = "USER_PASSWORD")
	private String user_password;
	private String user_passwordcypher; //암호화
	
	//이름
	private String user_name;
	private String user_namecypher; //암복호화
	private String user_namemasking; //마스킹
	
	//주민번호
	private String user_rrn;
	private String user_rrncypher; //암복호화
	
	//이메일
	@Column(name = "USER_EMAIL")
	private String useremail;
	private String user_emailcypher; //암복호화
	
	//로그인방식
	private String user_logintype;
	
	//성별
	private String user_gender;
	
	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_loginid() {
		return userLoginid;
	}

	public void setUser_loginid(String userLoginid) {
		this.userLoginid = userLoginid;
	}

	public String getUser_logincypher() {
		return user_logincypher;
	}

	public void setUser_logincypher(String user_logincypher) {
		this.user_logincypher = user_logincypher;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_passwordcypher() {
		return user_passwordcypher;
	}

	public void setUser_passwordcypher(String user_passwordcypher) {
		this.user_passwordcypher = user_passwordcypher;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_namecypher() {
		return user_namecypher;
	}

	public void setUser_namecypher(String user_namecypher) {
		this.user_namecypher = user_namecypher;
	}

	public String getUser_namemasking() {
		return user_namemasking;
	}

	public void setUser_namemasking(String user_namemasking) {
		this.user_namemasking = user_namemasking;
	}

	public String getUser_rrn() {
		return user_rrn;
	}

	public void setUser_rrn(String user_rrn) {
		this.user_rrn = user_rrn;
	}

	public String getUser_rrncypher() {
		return user_rrncypher;
	}

	public void setUser_rrncypher(String user_rrncypher) {
		this.user_rrncypher = user_rrncypher;
	}

	public String getUser_email() {
		return useremail;
	}

	public void setUser_email(String useremail) {
		this.useremail = useremail;
	}

	public String getUser_emailcypher() {
		return user_emailcypher;
	}

	public void setUser_emailcypher(String user_emailcypher) {
		this.user_emailcypher = user_emailcypher;
	}

	public String getUser_logintype() {
		return user_logintype;
	}

	public void setUser_logintype(String user_logintype) {
		this.user_logintype = user_logintype;
	}

}
