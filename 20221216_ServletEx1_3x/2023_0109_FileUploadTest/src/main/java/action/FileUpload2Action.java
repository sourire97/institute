package action;

import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUploadAction
 */
@WebServlet("/upload2.do")
public class FileUpload2Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /upload2.do?title=����&photo1=a.jpg&photo2=b.jpg
		
		String web_path  = "/upload/";
		String save_path = "";//"c:\\work\\upload"; //������ġ
		
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
		String filename1 = "no_file";
		String filename2 = "no_file";
		
		//���1)
		File  f1 = mr.getFile("photo1"); //getFile("parameter name")
		if(f1 != null) {
			filename1 = f1.getName();
		}
		
		File  f2 = mr.getFile("photo2"); 
		if(f2 != null) {
			filename2 = f2.getName();
		}
		
		//���2)
		/*
		filename = mr.getOriginalFileName("photo");
		if(filename==null)
			filename = "no_file";
		*/
		
		//�Ķ���� ���� ���ϱ�(����:���Ͼ��ε�ÿ��� mr�� �̿��ؾ� �Ѵ�)
		//String title = request.getParameter("title"); (X)
		String title = mr.getParameter("title"); //(O)
		
		//request binding
		request.setAttribute("title", title);
		request.setAttribute("filename1", filename1);
		request.setAttribute("filename2", filename2);
		
		

		//forward
		String forward_page = "result_photo2.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
