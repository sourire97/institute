package action.member;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberCheckIdAction
 */
@WebServlet("/member/check_id.do")
public class MemberCheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /member/check_id.do?mem_id=one
		
		//1.수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		String mem_id = request.getParameter("mem_id");
		
		//3.mem_id 있는지 검사
		MemberVo vo = MemberDao.getInstance().selectOne(mem_id);
		
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

