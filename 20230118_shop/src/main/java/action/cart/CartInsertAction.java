package action.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.CartDao;
import vo.CartVo;

/**
 * Servlet implementation class MemberModifyAction
 */
@WebServlet("/product/cart_update.do")
public class CartInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /product/cart_insert.do?p_idx=22&mem_idx=1
				
		//1.parameter받기
		int p_idx		= Integer.parseInt(request.getParameter("p_idx"));
		int mem_idx		= Integer.parseInt(request.getParameter("mem_idx"));
		
		//2.CartVo포장
		CartVo vo = new CartVo();
		vo.setP_idx(p_idx);
		vo.setMem_idx(mem_idx);
		 
		//3. p_idx & mem_idx 장바구니 정보 검색
		CartVo resVo = CartDao.getInstance().selectOne(vo);
		
		String result = "success";
		
		//이미 등록된 경우
		if(resVo!=null) {
			
			result = "exist";
			
		}else {
			
			//등록되어있지 않은 경우, DB update
			int res = CartDao.getInstance().insert(vo);
		}
		
		//4. 결과 json생성
		JSONObject json = new JSONObject();
		json.put("result", result);
		
		//5. 결과 전송
		response.setContentType("text/json; charset=utf-8;");		
		response.getWriter().print(json.toString());
	}

}
