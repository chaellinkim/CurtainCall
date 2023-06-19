package com.cc.model.mapper;

import org.springframework.stereotype.Component;

import com.cc.model.dto.UserPlayDto;
import com.cc.model.entity.UserPlay;

@Component
public class UserPlayMapper {
	public UserPlayDto toDto(UserPlay userPlay) {
        UserPlayDto userPlayDto = new UserPlayDto();
        
        userPlayDto.setMerchantUid(userPlay.getMerchantUid());
        userPlayDto.setUserId(userPlay.getUserId());
        userPlayDto.setPlayId(userPlay.getPlayId());
        userPlayDto.setPlayTitle(userPlay.getPlayTitle());
        
        return userPlayDto;
    }

    public UserPlay toEntity(UserPlayDto userPlayDto) {
        UserPlay userPlay = new UserPlay();
        
        userPlay.setMerchantUid(userPlayDto.getMerchantUid());
        userPlay.setUserId(userPlayDto.getUserId());
        userPlay.setPlayId(userPlayDto.getPlayId());
        userPlay.setPlayTitle(userPlayDto.getPlayTitle());
        
        return userPlay;
    }
}
