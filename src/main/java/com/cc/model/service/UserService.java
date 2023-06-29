package com.cc.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cc.model.cypher.SHA256;
import com.cc.model.entity.User;
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

	  /** 임시 비밀번호 생성 **/
    public String getTmpPassword() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String pwd = "";

        /* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
        int idx = 0;
        for(int i = 0; i < 10; i++){
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }

        return pwd;
    }

    
    /** 임시 비밀번호로 업데이트 
     * @throws Exception **/
    public int updatePassword(String tmpPassword, String userEmail) throws Exception {
    	SHA256 sha256 = new SHA256();
    	
    	String tmpCypher = sha256.encrypt(tmpPassword); //임시비밀번호를 암호화
    	
    	return userRep.updatePassword(tmpCypher, tmpPassword, userEmail);
    }
}
