package com.example.springsandbox.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping("/")
	public String index() {
		logger.info("Root path called");
        return "Greetings from Spring Boot!";
	}

}