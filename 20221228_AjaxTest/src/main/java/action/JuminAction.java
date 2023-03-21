package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import myutil.Jumin;

/**
 * Servlet implementation class CalcAction
 */
@WebServlet("/jumin.do")
public class JuminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /calc.do?su1=10&su2=5
		
		//parameter받기
		String jumin_no = request.getParameter("jumin_no");
		
		//주민객체생성
		Jumin jumin = new Jumin();
		jumin.setJumin_no(jumin_no); //주민번호 넣기
		
		//JSON포장
		//String json = String.format("{\"plus\":%d , \"minus\":%d, \"multiply\":%d, \"divide\":%d}",
		//		                             plus,         minus,         multiply,    divide  );
		
		
		JSONObject json = new JSONObject();
		json.put("res_year", jumin.getYear());		
		json.put("res_age", jumin.getAge());		
		json.put("res_tti", jumin.getTti());		
		json.put("res_gender", jumin.getGender());		
		json.put("res_season", jumin.getSeason());		
		json.put("res_local", jumin.getLocal());		
		
		
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json.toString());
		
	}

}
