package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.TestService;

@Controller
public class TestController {

	@Autowired
	TestService test_service;
	
	public TestController() {
		// TODO Auto-generated constructor stub
		System.out.println("--3.TestController()--");
	}
	
	@RequestMapping("/test.do")
	public String test(Model model) {

		List list = test_service.selectTotalList();
		
		model.addAttribute("list", list);
		
		return "test";
	}
	
}
