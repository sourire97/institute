package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitListAction
 */
@WebServlet("/visit/insert.do")
public class VisitInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1. ���� ���ڵ� ����
		request.setCharacterEncoding("utf-8");
		
		//2. parameter�ޱ�
		/* visit/insert.do?name=d&content=d&pwd=d */
		String name = request.getParameter("name");
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		//content = "���ع���\r\n��λ���\r\n..."
		String pwd = request.getParameter("pwd");
		
		//3. �ۼ����� ������
		String ip = request.getRemoteAddr();
		
		//4. VisitDao ����
		VisitVo vo = new VisitVo(name, content, pwd, ip);
		
		//5. DB insert
		int res = VisitDao.getInstance().insert(vo);
		
		
		//������� �̵�
		response.sendRedirect("list.do");
		

	}

}
