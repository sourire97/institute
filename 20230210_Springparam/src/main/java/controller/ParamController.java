package controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.PersonVo;

@Controller
public class ParamController {

	public ParamController() {
		// TODO Auto-generated constructor stub
		System.out.println("--ParamController()--");
	}
	
	
	//입력폼 띄우기
	@RequestMapping("/insert_form.do")
	public String insert_form() {
	
		return "insert_form";
	}
	
	//param/insert1.do?name=일길동&age=30&tel=010-111-1234
	//낱개로 받기
	@RequestMapping("/insert1.do")
	@ResponseBody
	public String insert1(@RequestParam("name") String irum,
			              @RequestParam(value="age",defaultValue="0") int age,
			              String tel) {
		
		/*
		String name = request.getParameter("name");
		int    age  = Integer.parseInt(request.getParameter("age"));
		String tel  = request.getParameter("tel");
		*/
		System.out.println("---[수신된 파라메터]---");
		System.out.printf("이름:%s\n", irum);
		System.out.printf("나이:%s\n", age);
		System.out.printf("전화:%s\n", tel);
		
		
		return "success";
	}
	
	//객체로 받기
	
	@RequestMapping("/insert2.do")
	@ResponseBody
	public String insert2(@ModelAttribute PersonVo vo) {
		//@ModelAttribute 메소드인자:DS에게 요청하는 요청사항
		
		System.out.println("---[수신된 파라메터]---");
		System.out.printf("이름:%s\n", vo.getName());
		System.out.printf("나이:%s\n", vo.getAge());
		System.out.printf("전화:%s\n", vo.getTel());
		
		return "success";

	}
	
	//Map으로 받기
	
	//param/insert3.do?name=일길동&age=30&tel=010-111-1234
	@RequestMapping("/insert3.do")
	@ResponseBody
	public String insert3(@RequestParam Map map) {
		
		System.out.println(map);
		
		System.out.println("---[수신된 파라메터]---");
		System.out.printf("이름:%s\n", map.get("Name"));
		System.out.printf("나이:%s\n", map.get("Age"));
		System.out.printf("전화:%s\n", map.get("Tel"));
		
		return "success";
	}
	
		
	
	
	
}


