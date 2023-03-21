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
 * Servlet implementation class MemberModifyFormAction
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
		
		//1.�������ڵ� ����
		request.setCharacterEncoding("utf-8");
		
		//2.parameter�ޱ�
		String mem_id		=	request.getParameter("mem_id");
		String mem_pwd		=   request.getParameter("mem_pwd");
		
		//3.id�� �ִ��� Ȯ��
		MemberVo user = MemberDao.getInstance().selectOne(mem_id);
		
		//���̵� Ʋ�� ���
		if(user==null) {
			//�α��������� �ٽ� ����
			response.sendRedirect("login_form.do?reason=fail_id");
			return;
		}
		
		//��й�ȣ�� Ʋ�� ���
		if(user.getMem_pwd().equals(mem_pwd)==false) {
			//�α��������� �ٽ������ض�
			response.sendRedirect("login_form.do?reason=fail_pwd");
			return;
		}
		
		//�α���ó��
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		//������������ �̵�
		response.sendRedirect("list.do");
		
	}

}
