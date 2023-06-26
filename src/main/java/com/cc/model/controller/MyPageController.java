package com.cc.model.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.entity.Actor;
import com.cc.model.service.ActorService;

import java.util.Optional;
import com.cc.model.cypher.AES256;
import com.cc.model.cypher.SHA256;
import com.cc.model.repository.UserRepository;
import com.cc.model.repository.WishRepository;
import com.cc.model.service.UserService;
import com.cc.model.service.WishService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class MyPageController {

	AES256 aes256 = new AES256();
	SHA256 sha256 = new SHA256();
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private WishRepository wishRep;
	
	@Autowired
	private WishService wishSvc;
	
	@RequestMapping("/mypage")
	public String Mypage(HttpSession session, Model model)throws Exception {

		Integer id = (Integer) session.getAttribute("user_id"); //로그인한 유저 식별자 가져옴
		
		/*회원정보 탭*/
		//로그인한 유저의 정보를 가져옴
		Optional<User> mypage_user = userSvc.findOne(id);  
		
		String username = mypage_user.orElse(new User()).getUser_namecypher();
		String userid = mypage_user.orElse(new User()).getUser_logincypher();
		String useremail = mypage_user.orElse(new User()).getUser_emailcypher();
		String userrrn = mypage_user.orElse(new User()).getUser_rrncypher();
		model.addAttribute("user_state", session.getAttribute("user_state")); 

		model.addAttribute("user_id", aes256.decrypt(userid)); //유저 아이디
		model.addAttribute("username", aes256.decrypt(username)); //유저 이름
		model.addAttribute("useremail", aes256.decrypt(useremail)); //유저 이메일
		model.addAttribute("userrrn", aes256.decrypt(userrrn)); //유저 주민번호
		
		
		/*찜 목록 탭*/
		//로그인한 유저의 찜 목록을 가져옴
		List<Wish> mypage_wish = wishSvc.findAllByUserid(id);
		
		System.out.println(mypage_wish.size());
		for(Wish w:mypage_wish) {
			System.out.println(w.toString());
		}
		
		model.addAttribute("mypage_wish", mypage_wish);
		
		/*나의 후기 탭*/
		
		/*예매 내역 탭*/
		
		return "mypage";
	}

	@PostMapping("/mypage_wish")
	public String WishDelete(Model model, String playtitle, String tabId) {
		
		if(wishSvc.deleteWish(playtitle) > 0) {
			return "redirect:/mypage?tabId=" + tabId;
		}else {
			model.addAttribute("msg", "error");
			return "redirect:/mypage?tabId=" + tabId;
		}
		
	}
}
