<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!-- JSTL를 이용하려면  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%
   //JSTL(JSP Standard Tag Library)
   //                  태그라이브러리:<c:if>형식의 명령
   // core : if  forEach  choose 
   // fmt  : 숫자  날짜
   // fn   : substring  indexOf ...
   // JSTL 내의 데이터는 EL표현법을 사용한다
   
   //숫자형
   int money = 120000000;
   
   //날짜형
   Date today = new Date();
   
   //문자열형식
   String str_date = "2022-12-21 15:44:50.0";
   
   
   // pageContext에 데이터를 넣는다.(binding)
   pageContext.setAttribute("money", money); //auto boxing이 되어서 new integer(money) 로 들어감
   pageContext.setAttribute("today", today);
   pageContext.setAttribute("str_date", str_date);
   
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	내가 소유하고 있는 현금 <fmt:formatNumber value="${ money }" /> <br>
	내가 소유하고 있는 현금 <fmt:formatNumber type="currency" value="${ money }" /> <br>
	내가 소유하고 있는 현금 ${ money } <br>
	
	<fmt:setLocale value="ko_kr"/>
	내가 소유하고 있는 현금 : <fmt:formatNumber  type="currency"   value="${ money }" /><br>
	
	<fmt:setLocale value="ja_jp"/>
	내가 소유하고 있는 현금 : <fmt:formatNumber  type="currency"   value="${ money }" /><br>
	
	<fmt:setLocale value="en_us"/>
	내가 소유하고 있는 현금 : <fmt:formatNumber  type="currency"   value="${ money }" /><br>
	
	현재의 날짜 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ today }"/><br>
	문자열 날짜(수정) : ${ fn:substring(str_date,0,16) }<br>
	문자열 날짜(기본) : ${ str_date }<br>
</body>
</html>