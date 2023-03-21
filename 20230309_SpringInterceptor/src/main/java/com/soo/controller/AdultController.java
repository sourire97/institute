package com.soo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adult/")
public class AdultController {

	@RequestMapping(value="main.do")
	public String main() {	
		
		return "adult/main";
	}
}
