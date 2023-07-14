package com.cc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.entity.Wish;
import com.cc.model.repository.WishRepository;

@Service
public class WishService {
	
	@Autowired
	private WishRepository wishRep;
	
	public List<Wish> findAllByUserid(int user_id){
		return wishRep.findByUserid(user_id);
	}
	
	public int deleteWish(String playtitle, int userid) {
        return wishRep.deleteByPlaytitle(playtitle, userid);
    }

	public Boolean insertWish(String playTitle, int userid, String playPoster) {
		Wish wish = new Wish();
		
		wish.setUserid(userid);
		wish.setPlaytitle(playTitle);
		wish.setPlayposter(playPoster);
		
		wishRep.save(wish);
		
		return true;
	}
}
