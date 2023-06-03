package com.cc.model.mapper;

import org.springframework.stereotype.Component;

import com.cc.model.dto.PlayDto;
import com.cc.model.entity.Play;

@Component
public class PlayMapper {
	public static PlayDto toDto(Play play) {
        PlayDto playDto = new PlayDto();
        playDto.setId(play.getPlayId());
        playDto.setPrfnm(play.getPlayTitle());
        playDto.setPcseguidance(play.getPlayPrice());
        playDto.setPrfpdfrom(play.getPlayFrom());
        playDto.setPrfpdto(play.getPlayTo());
        playDto.setDtguidance(play.getPlayTime());
        playDto.setPrfage(play.getPlayAge());
        playDto.setPoster(play.getPlayPoster());
        playDto.setMt10id(play.getPlaceId());
        return playDto;
    }

    public static Play toEntity(PlayDto playDto) {
        Play play = new Play();
        play.setPlayId(playDto.getId());
        play.setPlayTitle(playDto.getPrfnm());
        play.setPlayPrice(playDto.getPcseguidance());
        play.setPlayFrom(playDto.getPrfpdfrom());
        play.setPlayTo(playDto.getPrfpdto());
        play.setPlayTime(playDto.getDtguidance());
        play.setPlayAge(playDto.getPrfage());
        play.setPlayPoster(playDto.getPoster());
        play.setPlaceId(playDto.getMt10id());
        return play;
    }
}
