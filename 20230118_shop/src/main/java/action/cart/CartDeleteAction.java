package action.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;

/**
 * Servlet implementation class MemberDeleteAction
 */
@WebServlet("/product/delete.do")
public class CartDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1.parameter받기
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		
		//2.DB delete
		int res = CartDao.getInstance().delete(c_idx);
		
		//3.목록보기
		response.sendRedirect("cart_list.do");

	}

}
