<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
  <h3>부서별 사원목록</h3>
<hr>

<ul>
  <!-- for(DeptVo dept : dept_list) -->
  <c:forEach var="dept" items="${ dept_list }">
    <li>${dept.deptno} : ${dept.dname}
    	<ul>
    		<c:forEach var = "sawon" items="${ dept.sa_list }">
    			<li>${sawon.sabun} : ${sawon.saname}
    				<ul>
    					<c:forEach var = "gogek" items="${ sawon.go_list }">
    						<li>${gogek.gobun} : ${gogek.goname}</li>
    					</c:forEach>
    				</ul>
    			</li>
    		</c:forEach>
    	</ul>
    </li>
  </c:forEach>
</ul>

</body>
</html>