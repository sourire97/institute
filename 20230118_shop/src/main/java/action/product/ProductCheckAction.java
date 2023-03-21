package action.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.ProductDao;
import vo.ProductVo;

/**
 * Servlet implementation class MemberCheckIdAction
 */
@WebServlet("/product/check_num.do")
public class ProductCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /member/check_num.do?p_num=one
		
		//1.수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		String p_num = request.getParameter("p_num");
		
		//3.p_num 있는지 검사
		ProductVo vo = ProductDao.getInstance().selectOne(p_num);
		
		//boolean bResult = (vo==null);
		
		boolean bResult = false;
		
		if(vo==null) bResult = true;
		
		JSONObject json = new JSONObject();
		json.put("result", bResult);
		
		//결과 전송(응답)
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().print(json.toString());
		
	}

}

