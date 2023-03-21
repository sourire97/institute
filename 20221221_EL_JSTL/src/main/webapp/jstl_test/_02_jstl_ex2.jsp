<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!-- JSTL를 이용하려면  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<hr>
  forEach
<hr>
<c:forEach var="i" begin="1" end="5" step="1">
	i:안녕<br>
</c:forEach>

<!-- 변수는 pagecontext 안에 저장해두고 쓴다 -->
<c:forEach var="i" begin="1" end="5" step="1">

	<c:if test=""></c:if>

	<font color='red'>${ pageScope.i }:안녕<br>
</c:forEach>

<hr>
  forEach + if
<hr>
<c:forEach var="i"  begin="1"  end="5"  step="1">
	
	<c:if test="${ (i%2) == 1 }">
		<font color='red'>${ pageScope.i } : 안녕</font><br>
	</c:if>
	
	<c:if test="${ (i%2) == 0 }">
		<font color='blue'>${ pageScope.i } : 안녕</font><br>
	</c:if>
	
</c:forEach>

<hr>
  forEach + choose
<hr>

<c:forEach var="i"  begin="1"  end="5"  step="1">
   
   <c:choose>
      <c:when test="${ i%2==1 }"> 
          <font color='red'>${ pageScope.i } : 안녕</font><br>
      </c:when> 
      
      <c:when test="${ i%2==0 }"> 
          <font color='blue'>${ pageScope.i } : 안녕</font><br>
      </c:when>
   </c:choose>
   
   
</c:forEach>


</body>
</html>