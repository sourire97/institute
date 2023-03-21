package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DeptDao;
import vo.DeptVo;

@Controller
public class DeptController {

	DeptDao  dept_dao;

	public DeptController(DeptDao dept_dao) {
		super();
		this.dept_dao = dept_dao;
	}
	
	@RequestMapping("/dept/list.do")
	public String list(Model model) {
		
		List<DeptVo> list = dept_dao.selectList();
		
		//model을 통해서 list(data)를 DispacherServlet에게 전달
		//결과적으로 forward될 뷰의 request로 binding된다
		model.addAttribute("list", list);
		
		
		// DispacherServlet에게 ViewName을 반환하면 
		// DS가 ViewResolver에게  
		// prefix="/WEB-INF/views/" 
		// suffix=".jsp" 
		// 붙이도록 하고 forward시킨다
		return "dept/dept_list"; //ViewName
	}
	
	
	
	
	
}
