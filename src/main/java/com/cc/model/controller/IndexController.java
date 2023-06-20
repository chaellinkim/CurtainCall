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
import com.cc.model.dto.User;
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

		//ȸ������ �ÿ� �ʿ��� ����
		session.setAttribute("logintype", "local");
		
		return "login";
	}
	
	//�α���
	@PostMapping("/logincheck")
	public String logincheck(HttpSession session, Model model, String id, String password) throws NoSuchAlgorithmException {
		// ���⿡ ���̵�� ��й�ȣ Ȯ�����ְ�, db�� ����� ������ ��ġ�ϸ�
		// ���ǿ� ����� �ĺ��� �־��ֱ�(user_id)
		
		String url = "";
		int user_id = 0;
		
		Optional<User> user = userSvc.loginfindid(id);
		
		//id�� db�� �����ϴ��� ���� Ȯ��
		if(user.isEmpty()) {
				model.addAttribute("msg", "��ġ�ϴ� ȸ�������� �����ϴ�.");
				return "login";
		}else {
			String password_encode = sha256.encrypt(password);
			if(user.orElse(new User()).getUser_passwordcypher().equals(password_encode)) {
				//��й�ȣ ��ġ�ϴ� ���
				user_id = user.orElse(new User()).getUser_id();
				session.setAttribute("user_id", user_id); //�ĺ��� ����(����� ���� �ҷ�����)
				session.setAttribute("user_state", "login"); //�α����� �ȴٸ� user_state�� login�̶�� ���� �־��ֱ�
				session.setAttribute("password_encode", password_encode); //��ȣȭ�� ��й�ȣ --> �������������� ����Ϸ��� 
				model.addAttribute("user_id", session.getAttribute("user_id"));
				return "redirect:/";
			}else {
				//��й�ȣ ��ġ���� �ʴ� ��� 
				model.addAttribute("msg", "���̵� �Ǵ� ��й�ȣ�� Ȯ�����ּ���");
				return "login";
			}
		}
	}
	
	//ȸ������ �������� �Ѿ��
	@RequestMapping("/join")
	public String join(Model model, HttpSession session) {
		// name�� email ���� ����Ͽ� ó��
		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("email", session.getAttribute("email"));
		model.addAttribute("logintype", session.getAttribute("logintype"));
		return "join";
	}

	//ȸ������ ����
	//�����ϱ� ���� ����� �� �� --> ��ȣȭ, �̸� ����ŷ
	@PostMapping("/joincheck")
	public String joincheck(String login, String userId, String userPw, String userName, String rrn, String gender, String email) throws Exception {
		
		//�Ϻ�ȣȭ
		String idcypher =  aes256.encrypt(userId);
		String namecypher = aes256.encrypt(userName);
		String rrncypher = aes256.encrypt(rrn);
		String emailcypher = aes256.encrypt(email);
		
		//��ȣȭ
		String passwordcypher = sha256.encrypt(userPw);
		
		//����ŷ
		String maskingName = Masking(userName);
		
		if(userSvc.userInsert(userId, idcypher, userPw, passwordcypher, userName, namecypher, 
				maskingName, rrn, rrncypher, gender,email, emailcypher, login)) {
			
		}
		
//		userRep.userInsert(userId, idcypher, userPw, passwordcypher, userName, namecypher, maskingName, rrn, rrncypher, email, emailcypher, login);
		
		return "redirect:/";
	}
	
	
	//ȸ������ �̸� ����ŷ
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

	//���̵� �ߺ�üũ
	@ResponseBody
	@PostMapping("/idcheck")
	public int idcheck(String id) {
		int result;
		
		if(userSvc.loginidCheck(id)) {
			result = 1; //��� �Ұ�
		}else {
			result = 0; //��� ����
		}
		System.out.println(result);
		
		return result;
	}
}
