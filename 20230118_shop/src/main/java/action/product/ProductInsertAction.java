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
		
		//웹경로->절대경로 구하는 객체
		ServletContext application = request.getServletContext();
		save_path                  = application.getRealPath(web_path);
		System.out.println(save_path);
		
		int    max_size  = 1024*1024*100;      //업로드최대용량:100MB
		
		//화일업로드용 파라메터처리
		MultipartRequest mr = new MultipartRequest( request,   //request위임
				                                    save_path, //저장위치
				                                    max_size,  //저장크키  
				                                    "utf-8",   //수신인코딩
				                                    //이미화일이 존재하면 이름을 변경해서 업로드
				                                    new DefaultFileRenamePolicy() 
				                                    );
		//업로드된 화일정보 구하기
		String p_image_s= "no_file";
		String p_image_l= "no_file";
		
		//방법1)
		File  f1 = mr.getFile("p_photo_s"); //getFile("parameter name")
		if(f1 != null) {
			p_image_s = f1.getName();
		}
		
		File  f2 = mr.getFile("p_photo_l"); //getFile("parameter name")
		if(f2 != null) {
			p_image_l = f2.getName();
		}
		
		
		//파라메터 정보 구하기(주의:파일업로드시에는 mr을 이용해야 한다)
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
		
		//포장
		ProductVo vo = new ProductVo(category, p_num, p_name, p_company, p_price, p_saleprice, p_image_s, p_image_l, p_content);
		
		//DB insert
		int res = ProductDao.getInstance().insert(vo);
		
		//목록보기 이동(등록된 상품의 카테고리로 조회)
		response.sendRedirect("list.do?category=" + category);		
		

	}

}
