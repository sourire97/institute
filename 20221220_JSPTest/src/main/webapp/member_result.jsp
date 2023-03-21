<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    //Business Logic
    //POST방식일경우 수신인코딩 설정
	request.setCharacterEncoding("utf-8");
	
    String name 	=	request.getParameter("name");
    //System.out.println(name);
    String id		=	request.getParameter("id");
    String pwd		=	request.getParameter("pwd");
    String gender	=	request.getParameter("gender");
    String blood	=	request.getParameter("blood");
    String profile	=	request.getParameter("profile");
    
    // 취미 받기  : hobby=독서&hobby=영화(checkbox: check된 항목만 넘어온다)
    // String [] hobby_array= { "독서" , "영화"};
    String [] hobby_array = request.getParameterValues("hobby");
    String hobby_list = "취미없음";
    
    
    if(hobby_array !=null) {
    	
    	StringBuffer sb1 = new StringBuffer();
    	//개선 loop
    	for(String hobby : hobby_array) {
    		sb1.append(hobby);
    		sb1.append(" ");
    	}
    	
    	//sb1 => "독서 영화 "
    	hobby_list = sb1.toString().trim();// "독서 영화"
    }
    
    // 친구 받기 :  friend=&friend=&friend=&friend=
    // String [] friend_array = { "", "짱구", "철수", "" };
    String [] friend_array = request.getParameterValues("friend");
    
    String friend_list = "";
    
    StringBuffer sb2 = new StringBuffer();
    for(String friend : friend_array) {
    	
    	sb2.append(friend);
    	sb2.append(" ");
    }
    
    //sb2 = "    "  or " 짱구   " or " 짱구 철수  ";
    friend_list = sb2.toString().trim(); //"" or "짱구" or "짱구 철수"
    
    if(friend_list.isEmpty()) friend_list ="친구없음";
   
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
      <table class="table">
          <tr>
              <th class="success">이름</th>
              <td><%= name %></td>
          </tr>
          <tr>
              <th class="success">아이디</th>
              <td><%= id %></td>
          </tr>
          <tr>
              <th class="success">비밀번호</th>
              <td><%= pwd %></td>
          </tr>
          <tr>
              <th class="success">성별</th>
              <td><%= gender %></td>
          </tr>
          <tr>
              <th class="success">혈액형</th>
              <td><%= blood %></td>
          </tr>
          <tr>
              <th class="success">취미</th>
              <td><%= hobby_list %></td>
          </tr>
          <tr>
              <th class="success">친구</th>
              <td><%= friend_list %></td>
          </tr>
          <tr>
              <th class="success">자기소개</th>
              <td><%= profile %></td>
          </tr>
          
          <tr>
              <td colspan="2" align="center">
                  <a href='member.html'>다시하기</a>
              </td>
          </tr>
      </table>
  </div>
</body>
</html>