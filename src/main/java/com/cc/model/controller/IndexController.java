package com.cc.model.controller;

import java.security.NoSuchAlgorithmException;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.cypher.AES256;
import com.cc.model.cypher.SHA256;
import com.cc.model.entity.User;
import com.cc.model.repository.UserRepository;
import com.cc.model.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService userSvc;
	
	@Autowired
	private UserRepository userRep;
	
	AES256 aes256 = new AES256();
	SHA256 sha256 = new SHA256();

	@RequestMapping("/login")
	public String login(HttpSession session) {

		if (session.getAttribute("name") != null) {
			session.removeAttribute("name");
		}

		if (session.getAttribute("email") != null) {
			session.removeAttribute("email");
		}

		//회원가입 시에 필요한 정보
		session.setAttribute("logintype", "local");

		return "login";
	}

	//로그인
	@PostMapping("/logincheck")
	public String logincheck(HttpSession session, Model model, String id, String password) throws NoSuchAlgorithmException {
		// 여기에 아이디랑 비밀번호 확인해주고, db에 저장된 정보랑 일치하면
		// 세션에 사용자 식별자 넣어주기(user_id)
		
		String url = "";
		int user_id = 0;
		
		Optional<User> user = userSvc.loginfindid(id);
		
		//id가 db에 존재하는지 먼저 확인
		if(user.isEmpty()) {
				model.addAttribute("msg", "일치하는 회원정보가 없습니다.");
				return "login";
		}else {
			String password_encode = sha256.encrypt(password);
			if(user.orElse(new User()).getUser_passwordcypher().equals(password_encode)) {
				//비밀번호 일치하는 경우
				user_id = user.orElse(new User()).getUser_id();
				session.setAttribute("user_id", user_id); //식별자 저장(사용자 정보 불러오기)
				session.setAttribute("user_state", "login"); //로그인이 된다면 user_state에 login이라는 상태 넣어주기
				session.setAttribute("password_encode", password_encode); //암호화된 비밀번호 --> 마이페이지에서 사용하려공 
				model.addAttribute("user_id", session.getAttribute("user_id"));
				return "redirect:/";
			}else {
				//비밀번호 일치하지 않는 경우 
				model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요");
				return "login";
			}
		}
	}

	//회원가입 페이지로 넘어가기
	@RequestMapping("/join")
	public String join(Model model, HttpSession session) {
		// name과 email 값을 사용하여 처리
		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("email", session.getAttribute("email"));
		model.addAttribute("logintype", session.getAttribute("logintype"));
		return "join";
	}

	//회원정보 저장
	//저장하기 전에 해줘야 할 것 --> 암호화, 이름 마스킹
	@PostMapping("/joincheck")
	public String joincheck(String login, String userId, String userPw, String userName, String rrn, String gender, String email) throws Exception {
		
		//암복호화
		String idcypher =  aes256.encrypt(userId);
		String namecypher = aes256.encrypt(userName);
		String rrncypher = aes256.encrypt(rrn);
		String emailcypher = aes256.encrypt(email);
		
		//암호화
		String passwordcypher = sha256.encrypt(userPw);
		
		//마스킹
		String maskingName = Masking(userName);
		
		if(userSvc.userInsert(userId, idcypher, userPw, passwordcypher, userName, namecypher, 
				maskingName, rrn, rrncypher, gender,email, emailcypher, login)) {
			
		}
		
//		userRep.userInsert(userId, idcypher, userPw, passwordcypher, userName, namecypher, maskingName, rrn, rrncypher, email, emailcypher, login);
		
		return "redirect:/";
	}
	
	
	//회원정보 이름 마스킹
	public String Masking(String name) {
		
		String frsName = name.substring(0,1);
		String midName = name.substring(1,name.length()-1);
		String lstName = name.substring(name.length()-1, name.length());
		String maskingMid = "";
		
		for(int i = 0; i<midName.length(); i++) {
			maskingMid += "*";
		}
		
		return frsName+maskingMid+lstName ;
	}

	//아이디 중복체크
	@ResponseBody
	@PostMapping("/idcheck")
	public int idcheck(String id) {
		int result;
		
		if(userSvc.loginidCheck(id)) {
			result = 1; //사용 불가
		}else {
			result = 0; //사용 가능
		}
		System.out.println(result);
		
		return result;
	}
}
