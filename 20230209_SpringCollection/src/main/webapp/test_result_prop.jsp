<%@page import="util.MyProp"%>
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
    MyProp myProp = wc.getBean("myPropBean",MyProp.class);
    
    //pageContext.setAttribute("myList", myList);
    pageContext.setAttribute("myProp", myProp);
%>

<hr>
   <h3>DB접속정보</h3>
<hr>    
	<ul>
	   <!-- for( entry(key,val) : myProp.prop) -->
	   <c:forEach var="entry"  items="${ myProp.prop }">
	      <li>${ entry.key } : ${ entry.value }</li>
	   </c:forEach>
	</ul>







    
    
