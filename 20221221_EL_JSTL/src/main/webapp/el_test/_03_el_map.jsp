<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%     
	Map map = new HashMap();
	
	map.put("driver", "oracle.jdbc.driver.OracleDriver");
    map.put("url"   , "jdbc:oracle:thin:@localhost:1521:xe");
    map.put("user"  , "scott");
    map.put("pwd"   , "tiger");
    
    
    //EL로 표현하려면-> scope영역에 넣어야 한다
    //                  scope내에는 자바의 모든 객체 저장할 수 있다
    //                         K     V
    pageContext.setAttribute("map", map);
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
	<h5>Oracle Database Connection Option</h5>
<hr>

	<!-- .(dot)표기법 -->
	Driver	: ${ pageScope.map.driver } <br>
	URL		: ${ map.url } <br>
	
	<!-- square braket 표기법 -->
	User		: ${ map["user"] } <br>
	Password	: ${ map["pwd"] } <br>


</body>
</html>