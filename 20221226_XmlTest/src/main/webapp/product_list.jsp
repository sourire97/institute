
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
 #box{
    width: 800px;
    margin: auto;
    margin-top: 50px;
 }
 
 img{
     width: 120px;
     height: 100px;
 }

</style>

<!-- Bootstrap 3.x 사용설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
  function send(f){
	  
	  //입력값 체크
	  
	  
	  
	  f.action = "product_search.do";
	  f.submit();
  }


</script>  


</head>
<body>
   <div id="box">
      <form>
         상품명:<input name="p_name">
                <input type="button"  value="검색" onclick="send(this.form);">     
      </form>
      <hr>
      <table class="table">
          <!-- title -->
          <tr class="success">
               <th>이미지</th>          
               <th>상품명</th>          
               <th width="10%">최저가</th>          
               <th width="10%">최고가</th>          
               <th width="10%">쇼핑몰</th>          
          </tr>
          
          <!-- Data -->
          <!-- for(ProductVo p : list) 동일 -->
          <c:forEach var="p"  items="${ list }">
	          <tr>
	             <td><img src="${ p.image }"></td>
	             <td><a href="${ p.link }">${ p.title }</a></td>
	             <td><fmt:formatNumber type="currency"  value="${ p.lprice }"/> </td>
	             <td><fmt:formatNumber type="currency"  value="${ p.hprice }"/> </td>
	             <td>${ p.mallName }</td>
	          </tr>
          </c:forEach>
      </table>
   </div>
</body>
</html>




