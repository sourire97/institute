<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap 3.x 사용설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
 #box{
    width: 500px;
    margin: auto;
    margin-top: 50px;
 }

</style>  
  

</head>
<body>
  <div id="box">
     <table class="table table-hover">
        <!-- title -->
        <tr class="success">
           <th>사번</th>
           <th>이름</th>
           <th>성별</th>
           <th>직급</th>
        </tr>
        
        <!-- data -->
        <!-- for(DeptVo vo : list) 동일함  -->
        <c:forEach var="vo"  items="${ requestScope.list }"  varStatus="i">
           <tr>
               <td>${ i.count }</td>
               <td>${ vo.saname }</td>
               <td>${ vo.sasex }</td>
               <td>${ vo.sajob }</td>
           </tr>
        </c:forEach>
        
     </table>
  </div>


</body>
</html>