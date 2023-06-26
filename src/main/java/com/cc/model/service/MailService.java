package com.cc.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.model.dto.MailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MailService {

	@Autowired
    private JavaMailSender mailSender;

    /** 임시 비밀번호 이메일 생성 **/
    public MailDto createMail(String tmpPassword, String memberEmail) {
    	
        String title = "CurtainCall 임시 비밀번호 안내 이메일입니다.";
        String message = "안녕하세요. CurtainCall 임시 비밀번호 안내 메일입니다. "
                +"\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요."+"\n";
        String fromAddress = "CurtainCall.0326@gmail.com";

    	MailDto mailDto = new MailDto(memberEmail, title, message+tmpPassword, fromAddress);
        
    	return mailDto;
    }

    /** 이메일 전송 **/
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        
        mailMessage.setTo(mailDto.getToAddress()); //받는 사람 주소
        mailMessage.setSubject(mailDto.getTitle()); //제목
        mailMessage.setText(mailDto.getMessage()); //메세지 내용
        mailMessage.setFrom(mailDto.getFromAddress()); //보내는 사람 주소
        mailMessage.setReplyTo(mailDto.getFromAddress()); //답장 받을 메일 주소

        mailSender.send(mailMessage);
    }
    
    //아이디 찾기 메일 보내기
    public MailDto createIdMail(String memberId, String memberEmail) {
    	
        String title = "CurtainCall 아이디 안내 이메일입니다.";
        String message = "안녕하세요. CurtainCall 아이디 안내 메일입니다. "
                +"\n" + "회원님의 아이디는 아래와 같습니다."+"\n";
        String fromAddress = "CurtainCall.0326@gmail.com";

    	MailDto mailDto = new MailDto(memberEmail, title, message+memberId, fromAddress);
        
    	return mailDto;
    }
}