package com.soo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class MainController {

	@Autowired
	HttpSession session;
	
	@RequestMapping("main.do")
	public String main() {
		
		return "main/main";
	}
	
	//일반로그인
	@RequestMapping("login.do")
	public String login() {
		
		Map<String, String> user = new HashMap<String, String>();
		
		user.put("name", "김일반");
		user.put("grade", "일반");
		user.put("age", "16");
		
		//로그인정보를 세션에 넣기
		session.setAttribute("user", user);
		
		return "redirect:main.do";
	}

	//성인로그인
	@RequestMapping("login_adult.do")
	public String login_adult() {
		
		Map<String, String> user = new HashMap<String, String>();
		
		user.put("name", "김성인");
		user.put("grade", "일반");
		user.put("age", "30");
		
		//로그인정보를 세션에 넣기
		session.setAttribute("user", user);
		
		return "redirect:main.do";
	}
	
	//관리자로그인
	@RequestMapping("login_admin.do")
	public String login_admin() {
		
		Map<String, String> user = new HashMap<String, String>();
		
		user.put("name", "김관리");
		user.put("grade", "관리자");
		user.put("age", "20");
		
		//로그인정보를 세션에 넣기
		session.setAttribute("user", user);
		
		return "redirect:main.do";
	}
	
	@RequestMapping("logout.do")
	public String logout() {
		
		session.removeAttribute("user");
		
		return "redirect:main.do";
	}
}
