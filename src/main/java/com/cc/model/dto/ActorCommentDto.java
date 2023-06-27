package com.cc.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
public class ActorCommentDto {
	
	private int comment_id;
	private long actor_id;
	private String user_loginid;
	private String content;
	private LocalDateTime created;
	
}
