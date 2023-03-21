package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SawonDao;
import vo.SawonVo;

@Controller
public class SawonController {
	
	SawonDao sawon_dao;

	public SawonController(SawonDao sawon_dao) {
		super();
		this.sawon_dao = sawon_dao;
	}
	
	@RequestMapping("/sawon/list.do")
	public String list(Model model) {
	
		List<SawonVo> list = sawon_dao.selectList(); 
		
		//model�� ���ؼ� list(data)�� DispacherServlet���� ����
		//��������� forward�� ���� request�� binding�ȴ�
		model.addAttribute("list", list);
		
		return "sawon/sawon_list";
	}
}
	
