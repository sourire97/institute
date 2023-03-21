package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@WebServlet("/visit/list.do")
public class VisitListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /visit/list.do
		// /visit/list.do?search=name&search_text=길동
		
		//1.수신인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		String search      = request.getParameter("search");
		String search_text = request.getParameter("search_text");
		
		if(search==null) search = "all";
		
		//검색조건을 담을 map
		Map<String,String> map = new HashMap<String,String>();
		
		if(search.equals("name_content")) {
			//이름+내용
			map.put("name", search_text);
			map.put("content", search_text);
		}else if(search.equals("name")) {
			//이름
			map.put("name", search_text);
		}else if(search.equals("content")) {
			//내용
			map.put("content", search_text);
		}else if(search.equals("modifydate")) {
			//작성일자
			map.put("modifydate", search_text);
		}
		
		
		//목록 가져오기
		List<VisitVo> list = VisitDao.getInstance().selectList(map);
		
		//request binding
		request.setAttribute("list", list);

		//forward
		String forward_page = "visit_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
