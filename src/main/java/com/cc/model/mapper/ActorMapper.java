package com.cc.model.mapper;

import org.springframework.stereotype.Component;

import com.cc.model.dto.ActorDto;
import com.cc.model.entity.Actor;

@Component
public class ActorMapper {
	public static ActorDto toDto(Actor actor) {
		ActorDto actorDto = new ActorDto();
		actorDto.setId(actor.getActorId());
		actorDto.setName(actor.getActorName());
		actorDto.setImg(actor.getActorImg());
		actorDto.setBirth(actor.getActorBirth());
		actorDto.setSns(actor.getActorSNS());
		return actorDto;
	}
	
	public static Actor toEntity(ActorDto actorDto) {
		Actor actor = new Actor();
		actor.setActorId(actorDto.getId());
		actor.setActorName(actorDto.getName());
		actor.setActorImg(actorDto.getImg());
		actor.setActorBirth(actorDto.getBirth());
		actor.setActorSNS(actorDto.getSns());
		return actor;
	}
}
