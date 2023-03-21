<%@page import="vo.PersonVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    PersonVo p = new PersonVo("일길동", 20,"010-111-1234");
    
    //EL표현하려면-> scope에 저장되어야 한다
    pageContext.setAttribute("p", p);
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름: ${ pageScope.p.name }<br> <!--  p.getName() call -->
나이: ${ p['age'] } <br>        <!--  p.getAge()  call -->
전화: ${ p.tel } <br>


</body>
</html>