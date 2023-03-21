package action.product;

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

import dao.ProductDao;
import vo.MemberVo;
import vo.ProductVo;

/**
 * Servlet implementation class ProductInsertAction
 */
@WebServlet("/product/insert.do")
public class ProductInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String web_path = "/images/";
		String save_path = ""; //"c:\\work\\upload"; //������ġ
				
		//����� -> ������ ���ϴ� ��ü
		ServletContext application = request.getServletContext();
		save_path = application.getRealPath(web_path);
		System.out.println(save_path);
		
		int max_size = 1024*1024*100; //�ִ� �뷮 100MB
		
		//file upload parameter
		MultipartRequest mr = new MultipartRequest(request,		//request ����
													save_path, 	//������ġ
													max_size, 	//����ũ��
													"utf-8", 	//�������ڵ�
													//���� ���� �����ϸ� �̸� �����ؼ� ���ε�
													new DefaultFileRenamePolicy()
													);
		
		//���ε�� ���� ���� ���ϱ�
		String p_image_s = "no_file";
		String p_image_l = "no_file";
		
		//���1
		File f1 = mr.getFile("p_image_s"); //getFile("parameter name")
			if(f1!=null) {
				p_image_s = f1.getName();
			}
		
		File f2 = mr.getFile("p_image_l"); //getFile("parameter name")
			if(f2!=null) {
				p_image_l = f2.getName();
			}
		
		//parameter ���ϱ�
		String category = mr.getParameter("category");
		String p_num = mr.getParameter("p_num");
		String p_name = mr.getParameter("p_name");
		String p_company = mr.getParameter("p_company");
		int p_price = Integer.parseInt(mr.getParameter("p_price"));
		int p_saleprice = Integer.parseInt(mr.getParameter("p_saleprice"));
		String p_content = mr.getParameter("p_content");
	
		//PhotoVo ����
		ProductVo vo = new ProductVo(category, p_num, p_name, p_company, p_price, p_saleprice, p_image_s, p_image_l, p_content);
				
		//DB insert
		int res = ProductDao.getInstance().insert(vo);
		
		//���� ȭ������ �̵�
		response.sendRedirect("list.do?category=" + category);
		

	}

}
