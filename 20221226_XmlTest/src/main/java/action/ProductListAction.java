package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MySearchUtil;
import vo.ProductVo;

/**
 * Servlet implementation class ProductListAction
 */
@WebServlet("/product_search.do")
public class ProductListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// product_search.do?p_name=TV&start=1&display=10
		
		//수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		int start   = 1;
		int display = 10;
		
		String p_name = request.getParameter("p_name");
		
		if(p_name==null) p_name = "Notebook";
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			display = Integer.parseInt(request.getParameter("display"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<ProductVo> list = MySearchUtil.search_shop(p_name, start, display);
		
		//System.out.println(list.size());
		//request binding
		request.setAttribute("list", list);
		
		//forward: 출력
		RequestDispatcher disp = request.getRequestDispatcher("product_list.jsp");
		disp.forward(request, response);
		
	}

}
