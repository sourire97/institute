<%@ page import="myutil.Jumin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    //Business Logic
    //수신인코딩 설정
	request.setCharacterEncoding("utf-8");

	///2022_1219_ServletParameter/jumin.do?jumin_no=801212-1234560
	//parameter 받기
	String jumin_no = request.getParameter("jumin_no");
	
	//주민객체생성
	Jumin jumin = new Jumin();
	jumin.setJumin_no(jumin_no); //주민번호 넣기
	
	//부가정보 추출
	int    year  	= jumin.getYear();
	int    age   	= jumin.getAge();
	String gender  	= jumin.getGender();
	String season	= jumin.getSeason();
	String local	= jumin.getLocal();
	String tti		= jumin.getTti();
	String ganji	= jumin.getGanji();
				

%>    
    
    
<!-- Presentation Logic  -->    
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
      box-shadow: 2px 2px 3px black;
      
   }
</style>


</head>
<body>

  <div id="box">
      <div class="panel panel-primary">
	      <div class="panel-heading">주민번호 부가정보 결과</div>
	      <div class="panel-body">
	         <table class="table">
	              <tr>
	                  <th class="success">주민번호</th>
	                  <td><%= jumin_no %></td>
	              </tr>
	              <tr>
	                  <th class="success">출생년도</th>
	                  <td><%= year %>(<%= ganji %>)</td>
	              </tr>
	              <tr>
	                  <th class="success">나이</th>
	                  <td><%= age %></td>
	              </tr>
	              <tr>
	                  <th class="success">띠</th>
	                  <td><%= tti %></td>
	              </tr>
	              <tr>
	                  <th class="success">성별</th>
	                  <td><%= gender %></td>
	              </tr>
	              <tr>
	                  <th class="success">지역</th>
	                  <td><%= local %></td>
	              </tr>
	              <tr>
	                  <th class="success">계절</th>
	                  <td><%= season %></td>
	              </tr>
	              
	              <tr>
	                  <td colspan="2" align="center">
	                      <a href='jumin.html'>다시하기</a>
	                  </td>
	              </tr>
	         </table>
	      </div>
    </div>
  
  </div>





</body>
</html>