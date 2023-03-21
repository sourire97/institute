<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap 3.x 사용설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
  <div id="box">
	<table class = 'table table-bordered table-striped table-hover'>
	  <tr class = 'success'>
	    <td>이름</td>
	    <td>${ requestScope.map.name }</td>
	  </tr>
	  
	  <tr>
	    <td>아이디</td>
	    <td>${ map.id }</td>
	  </tr>
	  
	  <tr>
	    <td>비밀번호</td>
	    <td>${ map['pwd'] }</td>
	  </tr> 
	    
	  <tr>
	    <td>성별</td>
	    <td>${ map.gender }</td>
	  </tr>
	  
	  <tr>
	    <td>혈액형</td>
	    <td>${ map.blood }</td>

	  </tr>
	  
	  <tr>
	    <td>취미</td>
	    <td>${ map.hobby_list }</td>
	  </tr>
	  
	  <tr>
	    <td>친구</td>
	    <td>${ map.friend_list }</td>
	  </tr>
	  <tr>
	    <td>자기소개</td>
	    <td>${ map.profile }</td>
	  </tr>
	  
	  <tr class="spec">
	    
	    
	    
	   
	  </tr>

	  
  </table>
</div>
</body>
</html>