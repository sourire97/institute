package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcAction
 */
@WebServlet("/calc.do")
public class CalcAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		///2022_1216_ServletEx1_3x/calc.do (X)
		///2022_1216_ServletEx1_3x/calc.do?su1=1&su2=0
		// Ŭ���̾�Ʈ�κ��� ���޵� ������(Parameter)�� Ÿ���� String�̴�
		//��ûó��
		
		String str_su1 = request.getParameter("su1");
		String str_su2 = request.getParameter("su2");
		
		int su1 = Integer.parseInt(str_su1);
		int su2 = Integer.parseInt(str_su2);
		
		
		//����ó�� (����) ���� ; ���� ���̴� ;���� ����
		response.setContentType("text/html; charset=utf-8;");
		
		PrintWriter out = response.getWriter();//���۰�ü
		
		//���� HTML�����ؼ� ����
		out.println("<html>");
		out.println("<head><title>������</title></head>");
		out.println("<body>");
		out.println("<h3>:::�����:::</h3>");
		out.printf("%d + %d = %d<br>", su1, su2, (su1+su2));
		out.printf("%d - %d = %d<br>", su1, su2, (su1-su2));
		out.printf("%d * %d = %d<br>", su1, su2, (su1*su2));
		
		if(su2==0) {
			out.println("0���δ� ������ �����ϴ�<br>");
		}else {
			
			out.printf("%d / %d = %d<br>",  su1, su2, (su1/su2));
			out.printf("%d %% %d = %d<br>", su1, su2, (su1%su2));
			
		}
		out.println("<a href='calc_test.html'>�ٽ��ϱ�</a>");
		
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}

}
