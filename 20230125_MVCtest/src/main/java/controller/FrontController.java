package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ListAction;
import action.ViewAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// URL http://localhost:9090/20230125_MVCtest/list.do
		// uri						/20230125_MVCtest/list.do
		
		String uri = request.getRequestURI();
		// System.out.println(uri);

		// uri /20230125_MVCtest/list.do
		// uri /20230125_MVCtest/view.do
		
		String forward_page="";

		if(uri.contains("list")) {

			ListAction action = new ListAction();
			forward_page = action.execute(request, response);
			
			//forward
			request.getRequestDispatcher(forward_page)
					.forward(request, response);
			
		}else if(uri.contains("view")) {
			// /view.do?book=Java
			//System.out.println("--view:상세보기--");
			ViewAction action = new ViewAction();
			forward_page = action.execute(request, response);
			
			//forward
			request.getRequestDispatcher(forward_page)
			       .forward(request, response);
		}
		
		
		//forward
		/*
		String forward_page = "";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
		*/
	}

}
