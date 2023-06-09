package com.cc.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDto {
	
	@Id
	private int user_id; //식별자
	
	//아이디
	private String loginid;
	private String logincypher; //암복호화
	
	//비밀번호
	private String password;
	private String passwordcypher; //암호화
	
	//이름
	private String name;
	private String namecypher; //암복호화
	private String namemasking; //마스킹
	
	//주민번호
	private String rrn;
	private String rrncypher; //암복호화
	
	//이메일
	private String email;
	private String emailcypher; //암복호화
	
	//로그인방식
	private String logintype;
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	
	public String getLogincypher() {
		return logincypher;
	}
	public void setLogincypher(String logincypher) {
		this.logincypher = logincypher;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordcypher() {
		return passwordcypher;
	}
	public void setPasswordcypher(String passwordcypher) {
		this.passwordcypher = passwordcypher;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNamecypher() {
		return namecypher;
	}
	public void setNamecypher(String namecypher) {
		this.namecypher = namecypher;
	}
	
	public String getNamemasking() {
		return namemasking;
	}
	public void setNamemasking(String namemasking) {
		this.namemasking = namemasking;
	}
	
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	
	public String getRrncypher() {
		return rrncypher;
	}
	public void setRrncypher(String rrncypher) {
		this.rrncypher = rrncypher;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmailcypher() {
		return emailcypher;
	}
	public void setEmailcypher(String emailcypher) {
		this.emailcypher = emailcypher;
	}
	
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	
	
}
