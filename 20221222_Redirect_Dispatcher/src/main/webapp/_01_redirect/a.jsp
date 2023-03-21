<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

     System.out.println("a.jsp 왔었습니다");
     //클라이언트에게 b.jsp로 재요청하라고 응답
     response.sendRedirect("b.jsp");


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