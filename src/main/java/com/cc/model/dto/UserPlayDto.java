package com.cc.model.dto;

import lombok.Data;

@Data
public class UserPlayDto {
	private String merchantUid;
	private long userId;
	private String playId;
	private String playTitle;
}
