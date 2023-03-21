<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    //쿠키정보 읽어오기
    Cookie [] c_array = request.getCookies();
    StringBuffer sb = new StringBuffer("<h3>[방문페이지]</h3>");
    if(c_array!=null){
    	
    	for(Cookie c1 : c_array){
    		
    		//System.out.printf("name:%s value:%s\n", c1.getName(),c1.getValue());
    		String name = c1.getName();
    		String value= c1.getValue();
    		
    		if(name.equals("JSESSIONID")==false){
    			sb.append(String.format("<a href='%s'>%s</a><br>", value,name ));
    		}
    	}
    }

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  #popup{
      position: absolute;
      top: 50px;
      right:50px;
      width: 200px;
      height: 200px;
      padding: 20px;
      background: gray;
      color: yellow;
  }
  
  a{
      text-decoration: none;
      font-size: 30px;
      color: yellow;
  
  }
  
  body{
      background: #cccccc;
  }
</style>

</head>
<body>

<div id="popup"><%= sb.toString() %></div>
</body>
</html>