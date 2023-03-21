package action.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import vo.CartVo;

/**
 * Servlet implementation class MemberModifyAction
 */
@WebServlet("/product/cart_update.do")
public class CartUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /product/cart_update.do?c_idx=1&c_cnt=10
		
		//1.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		int    c_idx		=	Integer.parseInt(request.getParameter("c_idx"));
		int    c_cnt		=	Integer.parseInt(request.getParameter("c_cnt"));
		
		//3.MemberVo포장
		CartVo vo = new CartVo();
		vo.setC_idx(c_idx);
		vo.setC_cnt(c_cnt);
		 
		//4.DB update
		int res = CartDao.getInstance().update(vo);
		
		//5.목록보기
		response.sendRedirect("list.do");		
		

	}

}
