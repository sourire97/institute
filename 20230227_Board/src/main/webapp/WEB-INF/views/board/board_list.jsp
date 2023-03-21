<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    
    
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
       width: 1024px;
       margin: auto;
       margin-top: 20px;
   }
   
   #login{
       text-align: right;
   }
   
</style>  

</head>
<body>
  <div id="box">
     <h1 id="title">::::게시판::::</h1>
     <div id="login">
         <!-- 로그인안된상태 -->
         <c:if test="${ empty sessionScope.user }">
             <input class="btn btn-primary"  type="button"  value="로그인" 
                    onclick="location.href='../member/login_form.do'">
         </c:if>
         
         <!-- 로그인 된상태 -->
         <c:if test="${ not empty sessionScope.user }">
             <b>${ user.mem_name }</b>님 환영합니다
             <input class="btn btn-primary"  type="button"  value="로그아웃" 
                    onclick="location.href='../member/logout.do'">
         </c:if> 
     
     </div>
     
     <div>
         <input class="btn btn-primary"  type="button" value="글쓰기">
     </div>
     
     <table class="table table-striped">
     
     </table>
     
  </div>
</body>
</html>