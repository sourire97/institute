<%@page import="vo.PersonVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>    
    
<%

	List<PersonVo> p_list = new ArrayList<PersonVo>();

	p_list.add(new PersonVo("일길동", 21, "010-111-1234"));
	p_list.add(new PersonVo("이길동", 21, "010-222-1234"));
	p_list.add(new PersonVo("삼길동", 21, "010-333-1234"));
	p_list.add(new PersonVo("사길동", 21, "010-444-1234"));
	p_list.add(new PersonVo("오길동", 21, "010-555-1234"));
	
	//jstl + el 사용하기 위해서
	pageContext.setAttribute("p_list", p_list);
	
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  #box {
	width:
	margin:auto;
	margin-top: 50px;  
  }
</style>

<!-- Bootstrap 3.x 사용설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
  <div id="box">
	<table class="table">
	  <tr class="info">
	    <th>번호</th>
	    <th>이름</th>
	    <th>나이</th>
	    <th>전화번호</th>
	  </tr>
	  
	  <c:forEach var="p" items="${ pageScope.p_list }" varStatus="i">
	    <tr>
	      <td>${ i.count }</td>
	      <td>${ p.name }</td>
	      <td>${ p.age }</td>
	      <td>${ p.tel }</td>
	    </tr>
	  </c:forEach>
	  
	</table>
  </div>

</body>
</html>