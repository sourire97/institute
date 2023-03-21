package action.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import vo.CartVo;
import vo.MemberVo;

/**
 * Servlet implementation class CartListAction
 */
@WebServlet("/product/cart_list.do")
public class CartListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//세션에 보관돼 있는 로그인 정보 얻어오기
		MemberVo user = (MemberVo) request.getSession().getAttribute("user");
		
		//세션 만료된 경우
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=logout");
			
			return;
		}
		
		//회원번호
		int mem_idx = user.getMem_idx();
		
		//장바구니 목록 가져오기
		List<CartVo> list = CartDao.getInstance().selectList(mem_idx);
		
		//총액 가져오기
		int total_amount = CartDao.getInstance().selectTotalAmount(mem_idx);
		
		//request binding
		request.setAttribute("list", list);
		request.setAttribute("total_amount", total_amount);

		//forward
		String forward_page = "cart_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}

