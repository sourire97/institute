package action.member;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberModifyAction
 */
@WebServlet("/member/modify.do")
public class MemberModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /member/modify.do?mem_idx=1&mem_name=�ϱ浿&mem_id=one&mem_pwd=1234&
		//                   mem_zipcode=12345&mem_address=�����&mem_grade=������
		
		//1.�������ڵ� ����
		request.setCharacterEncoding("utf-8");
		
		//2.parameter�ޱ�
		int    mem_idx		=	Integer.parseInt(request.getParameter("mem_idx"));
		String mem_name		=	request.getParameter("mem_name");
		String mem_id		=	request.getParameter("mem_id");
		String mem_pwd		=   request.getParameter("mem_pwd");
		String mem_zipcode	=   request.getParameter("mem_zipcode");
		String mem_address	=   request.getParameter("mem_address");
		String mem_grade	=   request.getParameter("mem_grade");
		
		//3.MemberVo����
		MemberVo vo = new MemberVo(mem_idx, mem_name, mem_id, mem_pwd, mem_zipcode, mem_address, mem_grade);
		 
		//4.DB update
		int res = MemberDao.getInstance().update(vo);
		
		//5.��Ϻ���
		response.sendRedirect("list.do");		
		

	}

}
