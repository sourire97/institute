package action.photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoModifyAction
 */
@WebServlet("/photo/modify.do")
public class PhotoModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /photo/modify.do?p_idx=2&p_title=제3의인류&p_content=도서정보
		
		//1.수신 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.parameter 받기
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		String p_title = request.getParameter("p_title");
		String p_content = request.getParameter("p_content").replace("/n", "<br>");
		
		//3.ip받기
		String p_ip = request.getRemoteAddr();
		
		//4.포장
		PhotoVo vo = new PhotoVo(p_idx, p_title, p_content, p_ip);
		
		//5.DB update
		int res = PhotoDao.getInstance().update(vo);
		
		//6.메인화면
		response.sendRedirect("list.do");
		
	}

}
