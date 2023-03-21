<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	//접속자 IP
	String ip = request.getRemoteAddr();

	//Data Binding(request 통해서)
	request.setAttribute("ip", ip);
	
    //b.jsp를 서버내부에서 호출(Dispatch)
    RequestDispatcher disp = request.getRequestDispatcher("b.jsp");
    //전송(현재 갖고있는 request,response를 그대로 넘겨준다)
    disp.forward(request, response);
    
%>    
    


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 this is a.jsp
</body>
</html>