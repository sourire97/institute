package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//POJO(Plain Old Java Object) : 순수자바객체

public class ListAction {

	public String execute(HttpServletRequest request,HttpServletResponse response) {
		
		List<String> list = new ArrayList<String>();
		
		list.add("java");
		list.add("Oracle");
		list.add("html");
		list.add("css");
		list.add("Javascript");
		
		//request binding
		request.setAttribute("list", list);
		
		return "list.jsp";
	}
}
