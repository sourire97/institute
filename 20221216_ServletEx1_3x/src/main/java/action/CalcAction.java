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
		// 클라이언트로부터 전달된 데이터(Parameter)는 타입은 String이다
		//요청처리
		
		String str_su1 = request.getParameter("su1");
		String str_su2 = request.getParameter("su2");
		
		int su1 = Integer.parseInt(str_su1);
		int su2 = Integer.parseInt(str_su2);
		
		
		//응답처리 (주의) 정보 ; 정보 사이는 ;으로 구분
		response.setContentType("text/html; charset=utf-8;");
		
		PrintWriter out = response.getWriter();//전송객체
		
		//동적 HTML생성해서 전송
		out.println("<html>");
		out.println("<head><title>연산결과</title></head>");
		out.println("<body>");
		out.println("<h3>:::계산결과:::</h3>");
		out.printf("%d + %d = %d<br>", su1, su2, (su1+su2));
		out.printf("%d - %d = %d<br>", su1, su2, (su1-su2));
		out.printf("%d * %d = %d<br>", su1, su2, (su1*su2));
		
		if(su2==0) {
			out.println("0으로는 나눌수 없습니다<br>");
		}else {
			
			out.printf("%d / %d = %d<br>",  su1, su2, (su1/su2));
			out.printf("%d %% %d = %d<br>", su1, su2, (su1%su2));
			
		}
		out.println("<a href='calc_test.html'>다시하기</a>");
		
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}

}
