package com.cc.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.dto.User;
import com.cc.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRep;

//	//db에 아이디가 있는지 확인
	public Boolean loginidCheck(String loginid) {
		if (userRep.existsByUserLoginid(loginid))
			return true; // 사용 불가
		else {
			return false; //사용 가능
		}
	}
	
	public Optional<User> emailCheck(String email) {
		if(userRep.existsByUseremail(email)) {
			return userRep.findByUseremail(email);
		}else {
			return null;
		}
	}
	
	//로그인한 id로 select
	public Optional<User> loginfindid(String loginid){
		return userRep.findByUserLoginid(loginid); 
	}
	
	
	//회원등록
	public Boolean userInsert(String id,String idcypher,String password,String passwordcypher,String name,String namecypher, 
				String maskingName,String rrn,String rrncypher,String gender, String email,String emailcypher, String login) {
		User user = new User();
		
		user.setUser_loginid(id);
		user.setUser_logincypher(idcypher);
		user.setUser_password(password);
		user.setUser_passwordcypher(passwordcypher);
		user.setUser_name(name);
		user.setUser_namecypher(namecypher);
		user.setUser_namemasking(maskingName);
		user.setUser_rrn(rrn);
		user.setUser_rrncypher(rrncypher);
		user.setUser_email(email);
		user.setUser_emailcypher(emailcypher);
		user.setUser_logintype(login);
		user.setUser_gender(gender);
		userRep.save(user);
		
		return true;
	}

	//마이페이지로 회원정보 select
	public Optional<User> findOne(Integer user_id){
		
		return userRep.findById(user_id);
	}
}
