package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import vo.MemberVo;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoInsertAction
 */
@WebServlet("/photo/insert.do")
public class PhotoInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        //  /photo/insert.do?p_title=����&p_content=����&photo=a.jpg
		
		//���ǿ� �����Ǿ��ִ� �α������� ���´�
		MemberVo user = (MemberVo) request.getSession().getAttribute("user");
		
		//������ ���ᰡ �� ���
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=logout");
						
			return;
		}
		//ȸ����ȣ
		int mem_idx = user.getMem_idx();
		
		String web_path  = "/upload/";
		String save_path = "";
		
		//�����->������ ���ϴ� ��ü
		ServletContext application = request.getServletContext();
		save_path                  = application.getRealPath(web_path);
		//System.out.println(save_path);
		
		int    max_size  = 1024*1024*100;      //���ε��ִ�뷮:100MB
		
		//ȭ�Ͼ��ε�� �Ķ����ó��
		MultipartRequest mr = new MultipartRequest( request,   //request����
				                                    save_path, //������ġ
				                                    max_size,  //����ũŰ  
				                                    "utf-8",   //�������ڵ�
				                                    //�̹�ȭ���� �����ϸ� �̸��� �����ؼ� ���ε�
				                                    new DefaultFileRenamePolicy() 
				                                    );
		//���ε�� ȭ������ ���ϱ�
		String p_filename= "no_file";
		
		//���1)
		File  f = mr.getFile("photo"); //getFile("parameter name")
		if(f != null) {
			p_filename = f.getName();
		}
		
		//�Ķ���� ���� ���ϱ�(����:���Ͼ��ε�ÿ��� mr�� �̿��ؾ� �Ѵ�)
		String p_title   = mr.getParameter("p_title");   //(O)
		String p_content = mr.getParameter("p_content").replaceAll("\n", "<br>"); //(O)
		
		//IP���ϱ�(request���)
		String p_ip      = request.getRemoteAddr();  
		
		//PhotoVo����
		PhotoVo  vo = new PhotoVo(p_title, p_content, p_filename, p_ip, mem_idx);
		
		//DB insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//����ȭ������ �̵�
		response.sendRedirect("list.do");
				
		

	}

}
