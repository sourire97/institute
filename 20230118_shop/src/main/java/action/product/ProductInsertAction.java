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
        // /product/insert.do?category=com001&p_num=1&p_name=1&p_company=1&
		//                    p_price=1&p_saleprice=1&p_content=1&
		//                    p_photo_s=pds2.jpg&p_photo_l=pds2_z.jpg  
		
		String web_path  = "/images/";
		String save_path = "";
		
		//�����->������ ���ϴ� ��ü
		ServletContext application = request.getServletContext();
		save_path                  = application.getRealPath(web_path);
		System.out.println(save_path);
		
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
		String p_image_s= "no_file";
		String p_image_l= "no_file";
		
		//���1)
		File  f1 = mr.getFile("p_photo_s"); //getFile("parameter name")
		if(f1 != null) {
			p_image_s = f1.getName();
		}
		
		File  f2 = mr.getFile("p_photo_l"); //getFile("parameter name")
		if(f2 != null) {
			p_image_l = f2.getName();
		}
		
		
		//�Ķ���� ���� ���ϱ�(����:���Ͼ��ε�ÿ��� mr�� �̿��ؾ� �Ѵ�)
		String category  = mr.getParameter("category");
		String p_num 	 = mr.getParameter("p_num");
		String p_name 	 = mr.getParameter("p_name");
		String p_company = mr.getParameter("p_company");
		String p_content = mr.getParameter("p_content").replaceAll("\n", "<br>");
		
		int    p_price	   = 0;
		int    p_saleprice = 0;
		
		
		try {
			p_price 	= Integer.parseInt(mr.getParameter("p_price"));
			p_saleprice = Integer.parseInt(mr.getParameter("p_saleprice"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		//����
		ProductVo vo = new ProductVo(category, p_num, p_name, p_company, p_price, p_saleprice, p_image_s, p_image_l, p_content);
		
		//DB insert
		int res = ProductDao.getInstance().insert(vo);
		
		//��Ϻ��� �̵�(��ϵ� ��ǰ�� ī�װ��� ��ȸ)
		response.sendRedirect("list.do?category=" + category);		
		

	}

}
