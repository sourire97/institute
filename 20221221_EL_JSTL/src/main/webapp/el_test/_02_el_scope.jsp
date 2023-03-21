<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
    1.EL내에 표현할 수 있는 변수(객체)
      1) 각 scope에 저장된 변수(객체) 
         (pageScope,requestScope,sessionScope,applicationScope)
      2) parameter변수/header/쿠키...   

--%>    
    
<%     /*
    JSP 내장객체
    PageContext			pageContext;
    HttpServletRequest	requset;
    HttpSession			session;
    ServletContext		application;
    */
    
    //						  key		value
    pageContext.setAttribute("msg", "pageContext내의 값");
    request.setAttribute("msg", "request내의 값");
    session.setAttribute("msg", "session내의 값");
    application.setAttribute("msg", "application내의 값");
    
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- ${ 영역이름.변수명 } --%>
	pageContext : ${ pageScope.msg } <br>
	requestContext : ${ requestScope.msg } <br>
	sessionContext : ${ sessionScope.msg } <br>
	applicationContext : ${ applicationScope.msg } <br>
	
<%-- EL scope영역을 생략하면 아래순서대로 참조
     pageScope -> requestScope -> sessionScope -> applicationScope
 --%>
	이번에는 뭐가 출력? : ${ msg } <br>
</body>
</html>