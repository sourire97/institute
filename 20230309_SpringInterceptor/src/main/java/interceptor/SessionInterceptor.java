package interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//로그인 정보 얻어온다
		Map<String, String> user = (Map<String, String>) session.getAttribute("user");	
		
		if(user==null) {
			   
			response.sendRedirect("../main/main.do?reason=not_login");
			   
			return false;
		}
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		
		//관리자여부("/admin/")
		if(uri.contains("/admin/")) {
			
			if(user.get("grade").equals("관리자")==false) {
				
				response.sendRedirect("../main/main.do?reason=not_admin");
				   
				return false;
			}
		}
   
		//성인여부("/adult/")
		if(uri.contains("adult")) {

			int age = Integer.parseInt(user.get("age"));
			
			if(age<18) {
				
				response.sendRedirect("../main/main.do?reason=not_adult");
				   
				return false;
			}
		}
	
	   return super.preHandle(request, response, handler);
   }
	
}