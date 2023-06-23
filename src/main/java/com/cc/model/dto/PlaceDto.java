package com.cc.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDto {
	private String mt10id;
	private String fcltynm;
	private String adres;
	private String telno;
	private String relateurl;
	private BigDecimal la;
	private BigDecimal lo;

}


