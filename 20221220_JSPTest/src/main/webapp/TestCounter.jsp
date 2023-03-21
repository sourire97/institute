<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
     //단 1회만 
     int count = 0;
%>    

<%
    //_jspService() call(요청시마다)
    count++;
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%= count %>번째 방문하셨습니다

</body>
</html>