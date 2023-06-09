package com.cc.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userSvc;

	@RequestMapping("/login")
	public String login(HttpSession session) {

		if (session.getAttribute("name") != null) {
			session.removeAttribute("name");
		}

		if (session.getAttribute("email") != null) {
			session.removeAttribute("email");
		}

		// 회원가입 시에 필요한 정보
		session.setAttribute("logintype", "local");

		return "login";
	}

	// 로그인
	@PostMapping("/logincheck")
	public String logincheck() {
		// 여기에 아이디랑 비밀번호 확인해주고, db에 저장된 정보랑 일치하면
		// 세션에 사용자 식별자 넣어주기(user_id)
		return "redirect:/";
	}

	@RequestMapping("/join")
	public String join(Model model, HttpSession session) {
		// name과 email 값을 사용하여 처리
		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("email", session.getAttribute("email"));
		model.addAttribute("logintype", session.getAttribute("logintype"));
		return "join";
	}

	// 회원정보 저장
	@PostMapping("/login/joincheck")
	public String joincheck(String id, String password, String name, String userjumin) {

		return "";
	}

	// 아이디 중복체크
	@ResponseBody
	@PostMapping("/join/idcheck")
	public Map idcheck(String id) {
		boolean flag = userSvc.loginidCheck(id);
		Map map = new HashMap();
		map.put("flag", flag);

		return map;
	}

}
