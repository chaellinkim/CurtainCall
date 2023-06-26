package com.cc.model.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.model.cypher.AES256;
import com.cc.model.dto.MailDto;
import com.cc.model.entity.User;
import com.cc.model.service.MailService;
import com.cc.model.service.UserService;

/*아이디, 비밀번호 찾기 컨트롤러*/

@Controller
public class FindController {

	@Autowired
	UserService userSvc;
	
	@Autowired
	MailService mailSvc;

	AES256 aes256 = new AES256();

	@GetMapping("/login/idfind")
	public String IdFind() {

		return "id_find";
	}

	@GetMapping("/login/password_find")
	public String PasswordFind() {
		return "password_find";
	}

	/**비밀번호 찾기**/
	//아이디 확인
	@ResponseBody
	@PostMapping("/login/checkEmail")
	public String CheckEmail(@RequestParam("memberId") String memberId, @RequestParam("memberEmail") String memberEmail) throws Exception {
		String result;
		System.out.println(memberId + " , " + memberEmail);
		Optional<User> user = userSvc.loginfindid(memberId);
		
		// id가 db에 존재하는지 먼저 확인
		if (user.isEmpty()) {
			System.out.println("유저를 못 불러옴");
			result = "false";
			return result;
		} else {
			// 이메일 일치
			String email_encode = aes256.encrypt(memberEmail);
			if (user.orElse(new User()).getUser_emailcypher().equals(email_encode)) {
				System.out.println("이메일 일치");
				result = "true";
				return result;
			} else {
				// 이메일 일치하지 않는 경우
				System.out.println("이메일 불일치");
				result = "false";
				return result;
			}

		}
	}
	
	//임시비밀번호 전송
	@PostMapping("/login/sendPwd")
	public String SendPwd(@RequestParam("memberEmail") String memberEmail) throws Exception {

		
        /** 임시 비밀번호 생성 **/
        String tmpPassword = userSvc.getTmpPassword();

        /** 임시 비밀번호 저장 **/
        userSvc.updatePassword(tmpPassword, memberEmail);

        System.out.println(tmpPassword+" , "+ memberEmail);
        
        /** 메일 생성 & 전송 **/
        MailDto mail = mailSvc.createMail(tmpPassword, memberEmail);
        
        System.out.println(mail.toString());
        mailSvc.sendMail(mail);
        
		return "redirect:/login";
	}
	
	
	/**아이디 찾기**/
	//db에 이메일 있는지 확인(이메일로 유저 정보 가져와서 이름 확인(동명이인 때문에))
	@ResponseBody
	@PostMapping("/login/checkName")
	public Map<String, Object> CheckName(@RequestParam("memberName") String memberName, @RequestParam("memberEmail") String memberEmail) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		System.out.println(memberName + " , " + memberEmail);
		Optional<User> user = userSvc.emailCheck(memberEmail);
		
		// id가 db에 존재하는지 먼저 확인
		if (user.isEmpty()) {
			System.out.println("유저를 못 불러옴");
			result.put("success", "false");
			return result;
		} else {
			//이름 일치
			String name_encode = aes256.encrypt(memberName);
			if (user.orElse(new User()).getUser_namecypher().equals(name_encode)) {
				System.out.println("이메일 일치");
				result.put("success", "true");
				result.put("userId", user.orElse(new User()).getUser_logincypher());
				System.out.println("result :" + result);
				return result;
			} else {
				//이름 일치하지 않는 경우
				System.out.println("이메일 불일치");
				result.put("success", "false");
				return result;
			}
		}
	}
	
	/**아이디 찾기
	 * @throws Exception **/
	//아이디 
	@PostMapping("/login/sendId")
	public String SendId(@RequestParam("memberId") String memberId,@RequestParam("memberEmail")String memberEmail) throws Exception {
		
		
        /** 메일 생성 & 전송 **/
        MailDto mail = mailSvc.createIdMail(aes256.decrypt(memberId), memberEmail);
        
        System.out.println(mail.toString());
        mailSvc.sendMail(mail);
        
		return "redirect:/login";
	}
}
