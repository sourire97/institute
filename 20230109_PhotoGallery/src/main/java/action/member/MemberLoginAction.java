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
		// /member/login.do?mem_id=hong&mem_pwd=1234
		
		//1.수신인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		String mem_id  = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		
		
		//3.id확인
		MemberVo  user = MemberDao.getInstance().selectOne(mem_id);
		
		//아이디가 틀린경우
		if(user==null) {
			//로그인폼으로 다시접속해라
			response.sendRedirect("login_form.do?reason=fail_id");
			
			return;
		}
		
		//비밀번호가 틀린경우
		if(user.getMem_pwd().equals(mem_pwd)==false) {
			//로그인폼으로 다시접속해라
			response.sendRedirect("login_form.do?reason=fail_pwd&mem_id="+mem_id);
			return;
		}
		
		
		//로그인처리
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		//메인페이지로 이동
		response.sendRedirect("../photo/list.do");
		
		

	}

}
