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
		
		//1. 수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2. parameter받기
		/* visit/insert.do?name=d&content=d&pwd=d */
		String name = request.getParameter("name");
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		//content = "동해물과\r\n백두산이\r\n..."
		String pwd = request.getParameter("pwd");
		
		//3. 작성자측 아이피
		String ip = request.getRemoteAddr();
		
		//4. VisitDao 포장
		VisitVo vo = new VisitVo(name, content, pwd, ip);
		
		//5. DB insert
		int res = VisitDao.getInstance().insert(vo);
		
		
		//목록으로 이동
		response.sendRedirect("list.do");
		

	}

}
