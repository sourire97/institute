package action.cart;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

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

		String [] c_idx_array = request.getParameterValues("c_idx");
		
		//规过1
		/*
		  
		  for(String str_c_idx : c_idx_array) {
		  
		  int c_idx = Integer.parseInt(str_c_idx);
		  
		  int res = CartDao.getInstance().delete(c_idx);
		  
		  }
		 */
		
		//规过2
		Map map = new HashMap();
		map.put("c_idx_array", c_idx_array);
		
		int res = CartDao.getInstance().delete_all(map);
		
		response.sendRedirect("cart_list.do");
		

	}

}

