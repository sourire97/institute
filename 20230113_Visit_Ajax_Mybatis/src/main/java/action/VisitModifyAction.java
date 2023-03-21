package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class SungModifyAction
 */
@WebServlet("/visit/modify.do")
public class VisitModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//visit/modify.do?name=�̱浿&content=�ƽ���+����+1����+��+�־��µ�dd&pwd=1234
		
		//1.�������ڵ� ����
		request.setCharacterEncoding("utf-8");
		
		//2.parameter�ޱ�
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		//content = "���ع���\r\n��λ���\r\n..."
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		//3.SungVo����
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		//4.DB insert
		int res = VisitDao.getInstance().update(vo);
		
		//5.��Ϻ���� ���û
		response.sendRedirect("list.do");
		

	}

}
