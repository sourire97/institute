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
   
   #title{
       text-align: center;
       font-weight: bold;
       font-size: 30px;
       color: #3388aa;
       text-shadow: 1px 1px 2px black;
   }
   
   #login{
       text-align: right;
   }
   
   th,td{
       text-align: center;
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
     
     <div style="margin-bottom: 5px;">
         <input class="btn btn-primary"  type="button" value="글쓰기">
     </div>
     
     <!-- Data출력 -->
     <table class="table table-striped">
        <!-- title -->
        <tr class="success">
           <th width="10%">번호</th>
           <th width="40%">제목</th>
           <th width="20%">작성자</th>
           <th width="20%">작성일자</th>
           <th width="10%">조회수</th>
        </tr>
        
        <!-- data가 없는 경우 -->
        <c:if test="${ empty list }">
	        <tr>
	           <td colspan="5" align="center">
	              <span style="color:red;">게시물이 없습니다</span>
	           </td>
	        </tr>
        </c:if>
        
        <!-- data가 있는 경우 -->
        <!-- for(BoardVo vo : list)  -->
        <c:forEach var="vo"  items="${ list }">
            <tr>
                <td>${ vo.b_idx }</td>
                <td style="text-align: left;">
                   
                   <!-- b_depth만큼 들여쓰기 -->
                   <c:forEach begin="1"  end="${ vo.b_depth }">
                   		&nbsp;&nbsp;&nbsp;
                   </c:forEach>
                   
                   <!--답글이면 붙여라  -->
                   <c:if test="${ vo.b_depth != 0 }">
                   ㄴ
                   </c:if>
                   
                   <a href="view.do?b_idx=${ vo.b_idx }">${ vo.b_subject }</a>
                </td>
                
                <td>${ vo.mem_name }</td>
                <td>${ fn:substring(vo.b_regdate,0,16) }</td>
                <td>${ vo.b_readhit }</td>
            </tr>
        </c:forEach>
        
        
     </table>
     
  </div>
</body>
</html>