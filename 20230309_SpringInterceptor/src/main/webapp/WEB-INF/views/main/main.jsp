<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	//초기화
	window.onload = function(){
		
		setTimeout(showMessage, 100);
		
	};
	
	function showMessage(){
		
		if("${param.reason eq 'not_login'}" == "true"){
			alert("로그인이 필요한 컨텐츠 입니다.");
		}
		
		if("${param.reason eq 'not_admin'}" == "true"){
			alert("관리자만 접근 가능한 컨텐츠 입니다.");
		}
		
		if("${param.reason eq 'not_adult'}" == "true"){
			alert("성인만 접근 가능한 컨텐츠 입니다.");
		}
		
	}
	
</script>
</head>
<body>

<div>
     <!-- 로그인이 안된경우 화면 -->
     <c:if test="${ empty user }">
        <input type="button"  value="일반로그인" onclick="location.href='login.do'">
        <input type="button"  value="성인로그인" onclick="location.href='login_adult.do'">
        <input type="button"  value="관리로그인" onclick="location.href='login_admin.do'">
     </c:if>
     
     <!-- 로그인이 된경우 화면 -->
     <c:if test="${ not empty user }">
     	${ user.name }님 환영합니다(${ user.grade })(나이:${ user.age })
        <input type="button"  value="로그아웃" onclick="location.href='logout.do'">
     </c:if>
</div>


<a href="../admin/main.do">관리자페이지</a><br>
<a href="../adult/main.do">성인페이지</a><br>
</body>
</html>