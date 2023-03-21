<%@page import="util.MyMap"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
    WebApplicationContext wc 
       = WebApplicationContextUtils.getWebApplicationContext(application);
    // Spring Containter객체정보 얻어온다
    //                            bean_id     Type  
    MyMap myMap = wc.getBean("myMapBean",MyMap.class);
    
    //pageContext.setAttribute("myList", myList);
    pageContext.setAttribute("myMap", myMap);
%>

<hr>
   <h3>사전(영한/기술)</h3>
<hr>    
	<ul>
	   <!-- for( entry(key,val) : myMap.map) -->
	   <c:forEach var="entry"  items="${ myMap.map }">
	      <li>${ entry.key } 란? : ${ entry.value }</li>
	   </c:forEach>
	</ul>







    
    
