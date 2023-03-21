package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SawonDao;
import vo.SawonVo;


/**
 * Servlet implementation class DeptListAction
 */
@WebServlet("/sawon/list.do")
public class SawonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//데이터 가져오기
		List<SawonVo> list = SawonDao.getInstance().selectList();
		
		//데이터 못가져오는 문제 있으면 > ctrl + selectList() 눌러서 가져오는 부분에서 어디가 문제인지 확인. 이렇게 해도 가능한 이유가 sql connected 되었다는게 확인되었기 때문임.
		
		//System.out.println(list.size());
		
		//request binding
		request.setAttribute("list", list);
				
		//forward
		String forward_page = "sawon_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
		
	}

}
