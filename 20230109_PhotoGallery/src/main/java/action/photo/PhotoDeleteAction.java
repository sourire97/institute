package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoDeleteAction
 */
@WebServlet("/photo/delete.do")
public class PhotoDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
        //  /photo/delete.do?p_idx=3
		
		//화일의 저장경로 구하기
		String save_path = request.getServletContext().getRealPath("/upload/");
		
		int p_idx   =  Integer.parseInt(request.getParameter("p_idx"));
		//삭제할 화일명을 얻기 위해서
		PhotoVo  vo =  PhotoDao.getInstance().selectOne(p_idx);

		//화일 삭제 과정
		File  f = new File(save_path, vo.getP_filename());
		f.delete();
		
		//DB delete
	    int res = PhotoDao.getInstance().delete(p_idx);
	    
	    //메인화면...
	    response.sendRedirect("list.do");
		

	}

}
