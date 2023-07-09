package com.cc.model.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cc.model.cypher.AES256;
import com.cc.model.cypher.SHA256;
import com.cc.model.entity.User;
import com.cc.model.entity.Wish;
import com.cc.model.repository.UserRepository;
import com.cc.model.repository.WishRepository;
import com.cc.model.service.UserService;
import com.cc.model.service.WishService;

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

		@Autowired
	private ReviewService reviewSvc;
	
	@Autowired
	private ReviewRepository reviewRep;
		@Autowired
	private PlayService playSvc;
	
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
		List<Review> mypage_review = reviewRep.findByUser_id(id);
		List<User> user = userRep.findAll();
		
		//바이트 --> 문자열로 바꾸기 
		for (Review rev : mypage_review) {
	        byte[] imageData = rev.getReview_img();
	        String encodedImageData = Base64.encodeBase64String(imageData);
	        rev.setEncodedImage(encodedImageData);
	        
	        int UserId =rev.getUser_id();
	        
	        for(User u: user) {
	        	if(UserId == u.getUser_id()) {
	        		rev.setUserName(u.getUser_namemasking());
	        	}
	        }
	    }
		
		model.addAttribute("review", mypage_review);
		/*예매 내역 탭*/
		
		return "mypage";
	}

	//찜삭제
	@PostMapping("/mypage_wish")
	public String WishDelete(Model model, String playtitle, String tabId) {
		
		if(wishSvc.deleteWish(playtitle) > 0) {
			return "redirect:/mypage?tabId=" + tabId;
		}else {
			model.addAttribute("msg", "error");
			return "redirect:/mypage?tabId=" + tabId;
		}
		
	}

		// 이메일 변경
	@PostMapping("/mypage/info_modi")
	public String InfoUpdate(String email, HttpSession session) throws Exception {

		System.out.println("email:" + email);
		Integer id = (Integer) session.getAttribute("user_id");
		String newemail_cypher = aes256.encrypt(email);

		userSvc.modiEmail(id, email, newemail_cypher);

		return "redirect:/mypage?tabId=tab4";
	}

		// 비밀번호 변경
	@PostMapping("/mypage/passwordmodi")
	public String PasswordUpdate(String newpassword, HttpSession session) throws NoSuchAlgorithmException {

		Integer id = (Integer) session.getAttribute("user_id");
		String newpassword_cypher = sha256.encrypt(newpassword); // 새 비밀번호 암호화

		userSvc.modiPassword(id, newpassword, newpassword_cypher);

		return "redirect:/mypage?tabId=tab4";
	}

		// 현재 비밀번호 확인
	@ResponseBody
	@PostMapping("/mypage/pwcheck")
	public int PasswordCheck(HttpSession session, @RequestParam("password") String password)
			throws NoSuchAlgorithmException {

		Integer id = (Integer) session.getAttribute("user_id");
		String password_cypher = sha256.encrypt(password);

		Optional<User> mypage_user = userSvc.findOne(id);
		String user_password = mypage_user.orElse(new User()).getUser_passwordcypher();

		int result;

		if (password_cypher.equals(user_password)) {
			// 현재 비밀번호 일치
			result = 0;
		} else {
			result = 1;
		}

		return result;
	}
	
	//리뷰 업데이트
	@PostMapping("/mypage/update")
	public String updateReview(String reviewid, @RequestParam(name="img", required = false) MultipartFile img, String comment) throws IOException {
		
		int result = reviewSvc.updateReview(reviewid, img, comment);
		
		System.out.println(result);
		
		return "redirect:/mypage?tabId=tab2";
	}
	
	//리뷰 삭제
	@ResponseBody
	@PostMapping("/mypage/delete")
	public String deleteReview(@RequestParam("review_id") String review_id) {
		
		long id = Long.parseLong(review_id);
		
		reviewRep.deleteById(id);
		
		return "true";
	}
	
	//회원탈퇴
	@ResponseBody
	@PostMapping("/mypage/withdrawal")
	public String withdrawalUser(HttpSession session) {
		
		Integer id = (Integer) session.getAttribute("user_id");
		session.removeAttribute("user_state");
		session.removeAttribute("user_id");
		userSvc.deleteUser(id);
		
		return "true";
	}
}
