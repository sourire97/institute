package action.cart;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;

/**
 * Servlet implementation class CartDeleteAllAction
 */
@WebServlet("/product/cart_delete_all.do")
public class CartDeleteAllAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
        // /product/cart_delete_all.do?c_idx=8&c_idx=9&c_idx=10
		
		String [] c_idx_array = request.getParameterValues("c_idx");
		//String [] c_idx_array = { "8" , "9" , "10" };
		
		//规过1
		/*
		for(String str_c_idx : c_idx_array) {
			
			int c_idx = Integer.parseInt(str_c_idx);
			
			int res = CartDao.getInstance().delete(c_idx);
		}
		*/
		
		//规过2 : delete from cart where c_idx in (8,9,10)
		Map map = new HashMap();
		map.put("c_idx_array", c_idx_array);
		
		int res = CartDao.getInstance().delete_all(map);
				
		
		
		response.sendRedirect("cart_list.do");
		
		
	}

}
