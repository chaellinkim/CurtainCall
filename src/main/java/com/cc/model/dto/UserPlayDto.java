package com.cc.model.dto;

import lombok.Data;

@Data
public class UserPlayDto {
	private String merchantUid;
	private int userId;
	private String playId;
	private String playTitle;
	private String price;
	private String date;
	private String time;
	private String paymentDate;
}
