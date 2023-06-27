package com.cc.model;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class CurtainCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurtainCallApplication.class, args);
	}
}

