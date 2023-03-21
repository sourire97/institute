package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//POJO(Plain Old Java Object) : 순수자바객체

public class ViewAction {

	public String execute(HttpServletRequest request,HttpServletResponse response) {
		
		// /view.do?book=Java
		String book = request.getParameter("book");
		String content = "";
		
		switch(book.toUpperCase()){
		
		   case "JAVA"			: content="제임스 고슬링이 만든 언어로 전자제품 제어용 언어로 최초 만들었다..."; break;
		   case "ORACLE"		: content="현존하는 DBMS중 가장 성능이 좋은 데이터베이스 프로그램이다."; break;
		   case "HTML"			: content="HyperText Markup Language로 웹언어의 대표적인 언어이다."; break;
		   case "CSS"			: content="Cascade Style Sheet약자로 웹언어의 모양을 표현하는 언어이다"; break;
		   case "JAVASCRIPT"	: content="브라우저 제어용 언어이며, 이벤트등을 제어하는 언어이다."; break;
		}
		
		//request binding
		request.setAttribute("book", book);
		request.setAttribute("content", content);
		
		return "view.jsp";
	}
}
