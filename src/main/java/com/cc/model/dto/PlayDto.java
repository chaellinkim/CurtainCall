package com.cc.model.dto;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayDto {
	private String mt20id;
	private String prfnm;
	private String pcseguidance;
	private String prfpdfrom;
	private String prfpdto;
	private String dtguidance;
	private String prfage;
	private String poster;
	private String mt10id;
		
	
}
