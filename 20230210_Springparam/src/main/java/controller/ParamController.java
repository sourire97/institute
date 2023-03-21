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
	
	
	//�Է��� ����
	@RequestMapping("/insert_form.do")
	public String insert_form() {
	
		return "insert_form";
	}
	
	//param/insert1.do?name=�ϱ浿&age=30&tel=010-111-1234
	//������ �ޱ�
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
		System.out.println("---[���ŵ� �Ķ����]---");
		System.out.printf("�̸�:%s\n", irum);
		System.out.printf("����:%s\n", age);
		System.out.printf("��ȭ:%s\n", tel);
		
		
		return "success";
	}
	
	//��ü�� �ޱ�
	
	@RequestMapping("/insert2.do")
	@ResponseBody
	public String insert2(@ModelAttribute PersonVo vo) {
		//@ModelAttribute �޼ҵ�����:DS���� ��û�ϴ� ��û����
		
		System.out.println("---[���ŵ� �Ķ����]---");
		System.out.printf("�̸�:%s\n", vo.getName());
		System.out.printf("����:%s\n", vo.getAge());
		System.out.printf("��ȭ:%s\n", vo.getTel());
		
		return "success";

	}
	
	//Map���� �ޱ�
	
	//param/insert3.do?name=�ϱ浿&age=30&tel=010-111-1234
	@RequestMapping("/insert3.do")
	@ResponseBody
	public String insert3(@RequestParam Map map) {
		
		System.out.println(map);
		
		System.out.println("---[���ŵ� �Ķ����]---");
		System.out.printf("�̸�:%s\n", map.get("Name"));
		System.out.printf("����:%s\n", map.get("Age"));
		System.out.printf("��ȭ:%s\n", map.get("Tel"));
		
		return "success";
	}
	
		
	
	
	
}


