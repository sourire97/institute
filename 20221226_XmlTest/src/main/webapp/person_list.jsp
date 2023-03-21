<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  #box{
     width: 600px;
     margin: auto;
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
      <table class="table  table-hover">
          <!-- title -->
          <tr class="info">
             <th>번호</th>
             <th>이름</th>
             <th>별명</th>
             <th>나이</th>
             <th>휴대전화</th>
             <th>집전화</th>
          </tr>
          <!-- data -->
          
          <!--  for(PersonVo p : p_list)  -->
          <c:forEach var="p"  items="${ p_list }"  varStatus="i">
	          <tr>
	             <td>${ i.count }</td> 
	             <td>${ p.name }</td> 
	             <td>${ p.nickname }</td> 
	             <td>${ p.age }</td> 
	             <td>${ p.tel }</td> 
	             <td>${ p.hometel }</td> 
	          </tr>
          </c:forEach>
          
      </table>
  </div>
</body>
</html>