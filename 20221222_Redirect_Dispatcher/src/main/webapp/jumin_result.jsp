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
	    <td>연도</td>
	    <td>${ requestScope.map.year }</td>
	  </tr>
	  
	  <tr>
	    <td>나이</td>
	    <td>${ map.age }</td>
	  </tr>
	  	    
	  <tr>
	    <td>성별</td>
	    <td>${ map.gender }</td>
	  </tr>
	  

	  <tr>
	    <td>띠</td>
	    <td>${ map.tti }</td>
	  </tr>
	  
	  <tr>
	    <td>간지</td>
	    <td>${ map.ganji }</td>
	  </tr>

	  
	  
  </table>
</div>
</body>
</html>