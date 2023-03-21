<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>    
    
<%
	//배열
    String [] sido_array = { "서울", "경기","인천","대전","강원" };
    
    //개선 loop
	//for(String sido : sido_array){}  
    request.setAttribute("sido_array", sido_array);

    //ArrayList
    List fruit_list = new ArrayList();
    fruit_list.add("사과");
    fruit_list.add("참외");
    fruit_list.add("수박");
    fruit_list.add("딸기");
    fruit_list.add("포도");
    
    request.setAttribute("sido_array", sido_array);
    request.setAttribute("fruit_list", fruit_list);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
  시도 목록
<hr>
	<ul>
	   <!--  for(String sido : sido_array) 동일식 -->
	   <c:forEach var="sido"   items="${ requestScope.sido_array }"><%-- 공백넣지말기!주의! --%>
        	<li>${ pageScope.sido }</li>
       </c:forEach>	
	</ul>
	
<hr>
  과일 목록
<hr>
	<ul>
	   <!--  for(String fruit : fruit_list) 동일식 -->
	   <c:forEach var="fruit"   items="${ requestScope.fruit_list }"><%-- 공백넣지말기!주의! --%>
        	<li>${ pageScope.fruit }</li>
       </c:forEach>	
	</ul>




</body>
</html>