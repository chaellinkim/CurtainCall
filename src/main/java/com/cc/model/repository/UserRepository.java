package com.cc.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cc.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	//아이디가 있는지 확인
	//@Query("SELECT CASE WHEN COUNT(*) > 0 FROM USER WHERE USER_LOGINID=:loginid")
	boolean existsByUserLoginid(String loginid);
	
	boolean existsByUseremail(String email);
	
	//아이디에 해당하는 정보를 가져옴
	Optional<User> findByUserLoginid(String loginid);
	
	//이메일에 해당하는 정보를 가져옴
	Optional<User> findByUseremail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE USER SET USER_PASSWORD=?2,USER_PASSWORDCYPHER=?1 WHERE USER_EMAIL=?3")
	int updatePassword(String tmpCypher, String tmpPassword, String userEmail);

	@Transactional
	@Modifying
	@Query("UPDATE USER SET USER_PASSWORD=?2,USER_PASSWORDCYPHER=?3 WHERE USER_ID=?1")
	int modiPassword(int id, String newpassword, String newpassword_cypher);
	
	@Transactional
	@Modifying
	@Query("UPDATE USER SET USER_EMAIL=?2,USER_EMAILCYPHER=?3 WHERE USER_ID=?1")
	int modiEmail(int id, String email, String newemail_cypher);
	
	@Transactional
	@Modifying
	@Query("UPDATE USER SET USER_LOGINID='탈퇴한사용자', USER_LOGINCYPHER='탈퇴한사용자', USER_PASSWORD='탈퇴한 사용자',"
			+ "USER_PASSWORDCYPHER='탈퇴한 사용자', USER_NAME='탈퇴한 사용자', "
			+ "USER_NAMECYPHER='탈퇴한 사용자', USER_NAMEMASKING='탈퇴한 사용자', "
			+ "USER_RRN='탈퇴한 사용자', USER_RRNCYPHER='탈퇴한 사용자', USER_EMAIL='탈퇴한 사용자', "
			+ "USER_EMAILCYPHER='탈퇴한 사용자', USER_LOGINTYPE='탈퇴한 사용자', USER_GENDER='탈퇴한 사용자' WHERE USER_ID=?1")
	int deleteUser(int id);
}
