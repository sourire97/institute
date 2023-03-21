package action.member;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberDeleteAction
 */
@WebServlet("/member/delete.do")
public class MemberDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int mem_idx = Integer.parseInt(request.getParameter("mem_idx"));
		
		int res = MemberDao.getInstance().delete(mem_idx);
		
		//삭제한 유저가 로그인 상태면..로그아웃
		HttpSession session = request.getSession();
		MemberVo    user    = (MemberVo) session.getAttribute("user");
		
		if(user.getMem_idx() == mem_idx) {
			
			session.removeAttribute("user");
		}
		
		
		response.sendRedirect("list.do");
		

	}

}
