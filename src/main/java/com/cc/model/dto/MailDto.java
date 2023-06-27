package com.cc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

//@Getter

//@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    private String toAddress; // 받는 이메일 주소
    private String title; // 이메일 제목
    private String message; // 이메일 내용
    private String fromAddress; // 보내는 이메일 주소
    
	public String getToAddress() {
		return toAddress;
	}
	public String getTitle() {
		return title;
	}
	public String getMessage() {
		return message;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	

	public MailDto(String toAddress, String title, String message, String fromAddress) {
		this.toAddress = toAddress;
		this.title = title;
		this.message = message;
		this.fromAddress = fromAddress;
	}
	@Override
	public String toString() {
		return "MailDto [toAddress=" + toAddress + ", title=" + title + ", message=" + message + ", fromAddress="
				+ fromAddress + "]";
	}
    
	
    
}