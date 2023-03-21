package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberLoginAction
 */
@WebServlet("/member/login.do")
public class MemberLoginAction extends HttpServlet {
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
		String mem_pwd = request.getParameter("mem_pwd");
		
		String url = request.getParameter("url");
		
		//id 확인
		MemberVo user = MemberDao.getInstance().selectOne(mem_id);
		
		//아이디가 틀린 경우
		if(user==null) {
			//로그인 폼으로 다시 접속해라
			response.sendRedirect("login_form.do?reason=fail_id&url=" + url);
			return;
		}
		//비밀번호가 틀린 경우
		if(user.getMem_pwd().equals(mem_pwd)==false) {
			
			//response.sendRedirect("login_form.do?reason=fail_pwd&mem_id="+ mem_id + "&url=" + url);
			response.sendRedirect(String.format("login_form.do?reason=fail_pwd&mem_id=%s&url=%s", mem_id, url));
			return;
		}
		
		//로그인 처리
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		if(url.isEmpty()==false) {
			
			response.sendRedirect(url);
			
		}else {
			//메인 페이지로 이동
			response.sendRedirect("../product/list.do");
		}
		

	}

}

