package com.cc.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.model.dto.User;

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
	@Query("UPDATE USER SET USER_PASSWORD=?1,USER_PASSWORDCYPHER=?2 WHERE USER_EMAIL=?3")
	int updatePassword(String tmpCypher, String tmpPassword, String userEmail);
}
