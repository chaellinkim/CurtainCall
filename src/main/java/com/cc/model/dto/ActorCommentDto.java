package com.cc.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActorCommentDto {
	
	private int comment_id;
	private long actor_id;
	private String user_id;
	private String content;
	
}
