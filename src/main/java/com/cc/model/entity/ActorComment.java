package com.cc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ACTORCOMMENT")
public class ActorComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
	
	@Column(name = "user_loginid")
	private String userId;
	
	@Column(name="actorComment_content")
	private String acContent;
	
	@ManyToOne
	@JoinColumn(name="actor_id")
	private Actor actor;
	
}
