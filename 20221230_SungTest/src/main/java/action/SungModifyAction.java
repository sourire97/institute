package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SungTBDao;
import vo.SungVo;

/**
 * Servlet implementation class SungModifyAction
 */
@WebServlet("/sung/modify.do")
public class SungModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
        // /sung/modify.do?idx25&name=홍길동&kor=77&eng=88&mat=66
		
		//1.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		int    idx  = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		int    kor  = Integer.parseInt(request.getParameter("kor"));
		int    eng  = Integer.parseInt(request.getParameter("eng"));
		int    mat  = Integer.parseInt(request.getParameter("mat"));
		
		//3.SungVo포장
		SungVo vo = new SungVo(idx, name, kor, eng, mat);
		
		//4.DB insert
		int res = SungTBDao.getInstance().update(vo);
		
		//5.목록보기로 재요청
		response.sendRedirect("list.do");
		

	}

}
