package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myutil.Jumin;

/**
 * Servlet implementation class JuminAction
 */
@WebServlet("/jumin.do")
public class JuminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest  request, 
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//수신인코딩 설정
		request.setCharacterEncoding("utf-8");

		///2022_1219_ServletParameter/jumin.do?jumin_no=801212-1234560
		//parameter 받기
		String jumin_no = request.getParameter("jumin_no");
		
		//주민객체생성
		Jumin jumin = new Jumin();
		jumin.setJumin_no(jumin_no); //주민번호 넣기
		
		//부가정보 추출
		int    year  	= jumin.getYear();
		int    age   	= jumin.getAge();
		String gender  	= jumin.getGender();
		String season	= jumin.getSeason();
		String local	= jumin.getLocal();
		String tti		= jumin.getTti();
		String ganji	= jumin.getGanji();
				
		
		//응답처리...
		response.setContentType("text/html; charset=utf-8;");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>");
	    out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js'></script>");
	    out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table  class='table'>");
		out.printf("<tr><th>주민번호</th><td>%s</td></tr>", jumin_no);
		out.printf("<tr><th>출생년도</th><td>%d(%s)</td></tr>", year,ganji);
		out.printf("<tr><th>나이</th><td>%d(살)</td></tr>", age);
		out.printf("<tr><th>성별</th><td>%s</td></tr>", gender);
		out.printf("<tr><th>띠</th><td>%s</td></tr>", tti);
		out.printf("<tr><th>지역</th><td>%s</td></tr>", local);
		out.printf("<tr><th>계절</th><td>%s</td></tr>", season);
		out.println("<tr><td colspan='2' align='center'><a href='jumin.html'>다시하기</a></td></tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
