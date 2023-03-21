<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

  function cart_modify(c_idx){
	  
	  var c_cnt = document.getElementById("c_cnt_" + c_idx).value;
	  
	  //수정 Action호출 CartUpdateAction
	  location.href = "cart_update.do?c_idx=" + c_idx + "&c_cnt=" + c_cnt;
  }
  
  function cart_delete(c_idx){
	  
	  if(confirm("정말 삭제하시겠습니까?")==false) return;
	  	  
	  //삭제 Action호출 CartDeleteAction
	  location.href = "cart_delete.do?c_idx=" + c_idx;
  }

</script>
</head>

<%-- <jsp:include page="index.jsp"/> --%>
<%@include file="index.jsp" %>


<body>
	<table align="center" width="600" border="1"
 style="border-collapse:collapse;font-size:8pt"
 bordercolor="navy" cellpadding="4" cellspacing="0">
		<tr>
			<td colspan="6">:: 장바구니 내용</td>
		</tr>
		<tr bgcolor="#dedede">
			<th>제품번호</th>
			<th width="25%">제품명</th>
			<th>단가</th>
			<th>수량</th>
			<th>금액</th>
			<th>삭제</th>
		</tr>
		
		<c:forEach var="vo" items="${ list }">
			<tr align="center">
				<td>${ vo.p_num }</td>
				<td>${ vo.p_name }</td>
				<td>
					단가:<fmt:formatNumber value="${ vo.p_price }"/>원<br>
					<font color="red">
					세일가격:<b><fmt:formatNumber value="${ vo.p_saleprice }"/>원</b>
					</font>
				</td>
				<td>
					<!-- 수량 조정 폼 -->
					<input id="c_cnt_${ vo.c_idx }" size="4"  align="center" value="${ vo.c_cnt }">
					<input type="button" value="수정" onclick="cart_modify('${ vo.c_idx }');">
				</td>
				<td><fmt:formatNumber value="${ vo.amount }"/>원</td>
				<td>
					<input type="button" value="삭제"
						style="border:1 solid black;cursor:hand"
						onclick="cart_delete('${ vo.c_idx }')">
				</td>
			</tr>
		</c:forEach>
		
		<c:if test="${ empty list }">
			<tr>
				<td colspan="6" align="center">
					<b>장바구니가 비었습니다.</b>
				</td>
			</tr>
		</c:if>
		
		<tr>
			<td colspan="5" align="right">
				총 결제액 :
			</td>
			<td><fmt:formatNumber value="${ total_amount }"/>원</td>
		</tr>
	</table>
</body>
</html>






