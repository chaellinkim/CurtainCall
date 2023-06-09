package com.cc.model.google;

//import java.io.Console;
import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.cc.model.entity.User;
import com.cc.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiRestController {

	@Value("${google.auth.url}")
	private String googleAuthUrl;

	@Value("${google.login.url}")
	private String googleLoginUrl;

	@Value("${google.client.id}")
	private String googleClientId;

	@Value("${google.redirect.uri}")
	private String googleRedirectUri;

	@Value("${google.secret}")
	private String googleClientSecret;
	
	@Autowired
	private UserService userSvc;

	// 구글 로그인창 호출
	// https://accounts.google.com/o/oauth2/v2/auth?client_id=495904474836-tk1nqtp4mb7fdh6l6m013rvtm2bgf9gi.apps.googleusercontent.com&redirect_uri=http://localhost:8787/google-Redirection&response_type=code&scope=emailprofile
	
	@GetMapping(value = "/login/getGoogleAuthUrl")
	public ResponseEntity<?> getGoogleAuthUrl(HttpServletRequest request) throws Exception {

		String reqUrl = googleLoginUrl + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri="
				+ googleRedirectUri + "&response_type=code&scope=email%20profile%20openid&access_type=offline";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(reqUrl));
		
		// 1.reqUrl 구글로그인 창을 띄우고, 로그인 후 /login/oauth_google_check 으로 리다이렉션하게 한다.
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

	//login/oauth_google_check
	// 구글에서 리다이렉션
	@GetMapping(value = "/google-Redirection")
	public ModelAndView oauth_google_check(HttpServletRequest request, Model model, @RequestParam(value = "code") String authCode,
			HttpServletResponse response, HttpSession session) throws Exception {

		
		// 2.구글에 등록된 설정정보를 보내어 약속된 토큰을 받위한 객체 생성

		GoogleOAuthRequest googleOAuthRequest = new GoogleOAuthRequest(googleRedirectUri, googleClientId, googleClientSecret, authCode, "authorization_code");

//        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
//                .builder()
//                .clientId(googleClientId)
//                .clientSecret(googleClientSecret)
//                .code(authCode)
//                .redirectUri(googleRedirectUrl)
//                .grantType("authorization_code")
//                .build();
        
		RestTemplate restTemplate = new RestTemplate();

		// 3.토큰요청을 한다.
		ResponseEntity<GoogleLoginResponse> apiResponse = restTemplate.postForEntity(googleAuthUrl + "/token",
				googleOAuthRequest, GoogleLoginResponse.class);
		// 4.받은 토큰을 토큰객체에 저장
		GoogleLoginResponse googleLoginResponse = apiResponse.getBody();

		String googleToken = googleLoginResponse.getId_token();

		// 5.받은 토큰을 구글에 보내 유저정보를 얻는다.
		String requestUrl = UriComponentsBuilder.fromHttpUrl(googleAuthUrl + "/tokeninfo")
				.queryParam("id_token", googleToken).toUriString();

		// 6.허가된 토큰의 유저정보를 결과로 받는다.
		String resultJson = restTemplate.getForObject(requestUrl, String.class);
		
		JSONObject jsonObject = new JSONObject(resultJson);
		String name = jsonObject.getString("name");
		String email = jsonObject.getString("email");
		
		System.out.println(name + " " + email);
		ModelAndView modelAndView = null;
		
		Optional<User> user = userSvc.emailCheck(email);
		int user_id = 0;
		
		//db에 로그인한 아이디가 있는지 확인해주기 (없다면 null값)
		if(user != null) {
			user_id = user.orElse(new User()).getUser_id();
			session.setAttribute("user_id", user_id); //식별자 저장
			session.setAttribute("password_encode", user.orElse(new User()).getUser_passwordcypher()); //암호화된 비밀번호
			session.setAttribute("user_state", "login");
			model.addAttribute("user_id", session.getAttribute("user_id")); //확인용
			modelAndView = new ModelAndView("redirect:/");
		}else {
		
		//회원가입 할 때 가져오기 위한 정보를 세션에 저장
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		session.setAttribute("logintype", "google");
		
		modelAndView = new ModelAndView("redirect:/join");
		}
		
		return modelAndView;
	}
	
}