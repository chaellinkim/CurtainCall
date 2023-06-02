package com.cc.model.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.cc.model.dto.PlayDto;
import com.cc.model.entity.Play;

@Component
public class PlayMapper {
	public static PlayDto toDto(Play play) {
        PlayDto playDto = new PlayDto();
        playDto.setId(play.getPlay_id());
        playDto.setPrfnm(play.getPlay_title());
        playDto.setPcseguidance(play.getPlay_price());
        playDto.setPrfpdfrom(play.getPlay_from());
        playDto.setPrfpdto(play.getPlay_to());
        playDto.setDtguidance(play.getPlay_time());
        playDto.setPrfage(play.getPlay_age());
        playDto.setPoster(play.getPlay_poster());
        playDto.setMt10id(play.getPlace_id());
        return playDto;
    }

    public static Play toEntity(PlayDto playDto) {
        Play play = new Play();
        play.setPlay_id(playDto.getId());
        play.setPlay_title(playDto.getPrfnm());
        play.setPlay_price(playDto.getPcseguidance());
        play.setPlay_from(playDto.getPrfpdfrom());
        play.setPlay_to(playDto.getPrfpdto());
        play.setPlay_time(playDto.getDtguidance());
        play.setPlay_age(playDto.getPrfage());
        play.setPlay_poster(playDto.getPoster());
        play.setPlace_id(playDto.getMt10id());
        return play;
    }
}
