package com.cc.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class CurtainCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurtainCallApplication.class, args);
	}

	@RequestMapping("/")
	public String main() {
		
		return "main";
	}
}

