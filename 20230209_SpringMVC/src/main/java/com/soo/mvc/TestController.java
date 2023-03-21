package com.soo.mvc;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TestController {

	public TestController() {
		// TODO Auto-generated constructor stub
		System.out.println("--TestController()--");
	}
	
	@RequestMapping("/hello.do")
	@ResponseBody
	public String hello() {
		
		return "hi~ hello";
	}
	
	
	@RequestMapping("/test2.do")
	public ModelAndView test2() {
		
		String msg = "Hi~ Everyone";
		
		ModelAndView mv = new ModelAndView();
		
		//������ ������ DS => request binding ��Ų��
		mv.addObject("mgs", msg);
		
		//������ DS => ViewResolver��οϼ� => forward
		mv.setViewName("test2");
		
		return mv;
		
	}
	
}
