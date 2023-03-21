<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>    
    
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
      width: 800px;
      margin: auto;
      margin-top: 10px;
  }

</style>

<script type="text/javascript">
 
   window.onload = function(){
	  
	   if (navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(showPosition);
	   } else {
		    alert("Geolocation is not supported by this browser.");
       }
	   
	   document.getElementById("page").value = '${ empty param.page ? 1  : param.page }';
	   document.getElementById("size").value = '${ empty param.size ? 15 : param.size }';
	   document.getElementById("radius").value = '${ empty param.radius ? 1000 : param.radius }';
	   
	   
   };
   
   function showPosition(position) {
	   document.getElementById("latitude").value  = position.coords.latitude;
	   document.getElementById("longitude").value = position.coords.longitude;
  }
   

   

</script>



<script type="text/javascript">
   function send(f){
	   
	   
	   
	   
	   f.action="local_search.do";
	   f.submit();//누구에게 전송?
   }

</script>


</head>
<body>
  <div id="box">
    <!-- 검색메뉴 -->
    <form>
       <input type="hidden" name="latitude"   id="latitude"   value="">
       <input type="hidden" name="longitude"  id="longitude"  value="">
      
    검색명:<input name="keyword"  value="${ param.keyword }">
           <input type="button"  value="검색" onclick="send(this.form);">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;       
    페이지:<select name="page"  id="page">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
           </select> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;             
    페이지당 조회건수:
           <select name="size"  id="size">
              <option value="15">15</option>
              <option value="10">10</option>
              <option value="5">5</option>
           </select>  
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;       
    검색반경:              
           <select name="radius"  id="radius">
              <option value="1000">1000</option>
              <option value="500">500</option>
              <option value="100">100</option>
              <option value="2000">2000</option>
              <option value="3000">3000</option>
           </select>
    </form>
    
    <hr>
    
    <!-- 검색결과 출력 -->
    <table class="table">
        
        <tr class="info">
           <th>순번</th>
           <th>상호</th>
           <th>주소</th>
           <th>전화</th>
           <th>거리(m)</th>
           <th>위치(위도/경도)</th>
        </tr>
        
        <!-- 데이터가 없는경우 -->
        <c:if test="${ empty list }">
           <tr>
              <td colspan="6" align="center">
                  <font color="red">검색된 정보가 없습니다</font>
              </td>
           </tr>
        </c:if>
        
        <!-- 데이터가 있는경우 -->
        <c:forEach var="local"  items="${ list }"  varStatus="i">
        
          <c:if test="${ (start + i.index) <= local.pageable_count }">
            <tr>
               <td>${ start + i.index }</td>
               <td><a href="${ local.place_url }"  target="_blank">${ local.place_name }</a></td>
               <td>${ local.address }</td>
               <td>${ local.phone }</td>
               <td>${ local.distance }</td>
               <td>${ local.latitude }<br>${ local.longitude }</td>
            </tr>
          </c:if>  
        </c:forEach>
        
    
    </table>
    
  
  </div>


</body>
</html>