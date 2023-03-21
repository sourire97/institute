<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>

<!-- Bootstrap 3.x 사용설정 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
#box {
	width: 800px;
	margin: auto;
	margin-top: 20px;
}
</style>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="box">
		<div class="panel panel-primary">
			<div class="panel-heading">
			   <h4>[${ vo.mem_name }]님이 작성하신 글</h4>
			</div>
			<div class="panel-body">
			    <div id="subject">${ vo.b_subject }</div>
                <div id="content">
                    ${ vo.b_content }
                </div>
                <div id="regdate_ip">작성일자:${ vo.b_regdate } (${ vo.b_ip })</div>		
                <div>
                	<input	class="btn btn-primary" type="button" value="목록보기"
                			onclick="location.href='list.do'">
                	<input class="btn btn-success" type="button" value="답글쓰기">
                	
                	<!-- 로그인유저==게시물의 주인 같으면 -->
                	<c:if test="${ user.mem_idx eq vo.mem_idx }">
                		<input class="btn btn-info" type="button" value="수정하기">
                		<input class="btn btn-danger" type="button" value="삭제하기">
                	</c:if>
                	
            	</div>	
			</div>
		</div>
	</div>
</body>
</html>