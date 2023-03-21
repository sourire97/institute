<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
     //발급받은 세션 ID 얻어오기
     String session_id = session.getId();
     System.out.println(session_id);
     
     
     HttpSession session1 = request.getSession();
     String session_id1 = session1.getId();
     System.out.println(session_id1);
     
     //세션시간 지정
     session.setMaxInactiveInterval(300);
     
     System.out.println("--------------------------------");
     
     

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>