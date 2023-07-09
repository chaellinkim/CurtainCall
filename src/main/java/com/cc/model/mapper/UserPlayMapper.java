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
        userPlayDto.setPrice(userPlay.getPrice());
        userPlayDto.setDate(userPlay.getDate());
        userPlayDto.setTime(userPlay.getTime());
        userPlayDto.setPaymentDate(userPlay.getPaymentDate());
        
        return userPlayDto;
    }

    public UserPlay toEntity(UserPlayDto userPlayDto) {
        UserPlay userPlay = new UserPlay();
        
        userPlay.setMerchantUid(userPlayDto.getMerchantUid());
        userPlay.setUserId(userPlayDto.getUserId());
        userPlay.setPlayId(userPlayDto.getPlayId());
        userPlay.setPlayTitle(userPlayDto.getPlayTitle());
        userPlay.setPrice(userPlayDto.getPrice());
        userPlay.setDate(userPlayDto.getDate());
        userPlay.setTime(userPlayDto.getTime());
        userPlay.setPaymentDate(userPlayDto.getPaymentDate());
        
        return userPlay;
    }
}
