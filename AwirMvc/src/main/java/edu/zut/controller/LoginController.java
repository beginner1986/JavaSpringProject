package edu.zut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET) 		
	public String home(Model model) {
		
		log.info("WWW: Wejście na stronę logowania.");		
		
		return "login";
	}
	
}
