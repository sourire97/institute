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
		//visit/modify.do?name=이길동&content=아쉽네+내가+1등할+수+있었는데dd&pwd=1234
		
		//1.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		//content = "동해물과\r\n백두산이\r\n..."
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		//3.SungVo포장
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		//4.DB insert
		int res = VisitDao.getInstance().update(vo);
		
		//5.목록보기로 재요청
		response.sendRedirect("list.do");
		

	}

}
