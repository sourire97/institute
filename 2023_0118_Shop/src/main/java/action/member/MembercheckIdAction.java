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
 * Servlet implementation class MembercheckIdAction
 */
@WebServlet("/member/check_id.do")
public class MembercheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//parameter 받기
		String mem_id = request.getParameter("mem_id");
		
		//아이디 중복 검사
		MemberVo vo = MemberDao.getInstance().selectOne(mem_id);
		
		boolean bResult = false;
		
			if(vo==null)
				bResult = true;
		
		JSONObject json = new JSONObject();
		json.put("result", bResult);
		
		//결과 전송
		response.setContentType("text/json: charset=utf-8;");
		response.getWriter().print(json.toString());
	}

}

