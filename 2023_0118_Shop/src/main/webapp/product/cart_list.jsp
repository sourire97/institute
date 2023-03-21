<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<script type="text/javascript">

	function cart_modify(c_idx) {
		
		var c_cnt = document.getElementById("c_cnt_" + c_idx).value;
		
		//수정 Action 호출
		location.href="cart_update.do?c_idx=" + c_idx + "&c_cnt=" + c_cnt;

	}
	
	function cart_delete(c_idx) {
		
		if(confirm("정말 삭제하시겠습니까?")==false) return;
		
		//삭제 Action 호출
		location.href="cart_delete.do?c_idx=" + c_idx;

	}
	
	function delete_all(f) {
		
		//선택된 갯수가 0이면 체크 확인 알림 메시지
		var checked_count = $("input:checkbox[name='c_idx']:checked").length;
		
		if(checked_count==0) {
			alert("삭제할 상품을 체크하세요");
			return;
		}
	
		if(confirm("정말 삭제하시겠습니까?")==false) return;
	
		f.action = "cart_delete_all.do";
		f.submit();
		
	}
	
	$(document).ready(function() {
		 //만약 전체 선택 체크박스가 체크된 상태일 경우
	     $("#checkAll").click(function(){
	    	 
	    	//input type이 체크박스인것은 모두 체크함
	        if($("#checkAll").prop("checked")) {
	           $("input[type=checkbox]").prop("checked",true);
	                
	        //input type이 체크박스인것은 모두 체크 해제함  
	        } else {
	           $("input[type=checkbox]").prop("checked",false);
	                
	        }
	     });
	});
	
	$(document).ready(function() {
		
		$("#checkAll").click(function() {
			
			if($("#checkAll").is(":checked")) $("input[name=c_idx]").prop("checked", true);
			else $("input[name=c_idx]").prop("checked", false);
		});
		
		$("input[name=c_idx]").click(function() {
			var total = $("input[name=c_idx]").length;
			var checked = $("input[name=c_idx]:checked").length;
			
			if(total != checked) $("#checkAll").prop("checked", false);
			else $("#checkAll").prop("checked", true); 
		});
	});

</script>

</head>

<%@ include file="index.jsp" %>

<body>
<form>
	<div style="width: 600px; margin: auto; margin-bottom: 5px;">
	<input type="checkbox" id="checkAll">전체
	<input type="button" value="삭제" onclick="delete_all(this.form);">
	<input type="button" value="결제">
	</div>
	
	<table align="center" width="600" border="1" style="border-collapse:collapse; font-size:8pt"
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
			<td>
			<input type="checkbox" name="c_idx" value="${ vo.c_idx }">
			${ vo.p_num }
			</td>
			<td>${ vo.p_name }</td>
			<td>
				단가: <fmt:formatNumber value="${ vo.p_price }"/>원<br>
				<font color="red">
				세일가격: <b><fmt:formatNumber value="${ vo.p_saleprice }"/>원</b>
				</font>
			</td>
			<td>
				<!-- 수량 조정 폼 -->
				<input id="c_cnt_${ vo.c_idx }" size="4"  align="center" value="${ vo.c_cnt }">
				<input type="button" value="수정" onclick="cart_modify('${ vo.c_idx }');">
			</td>
			
			<td><fmt:formatNumber value="${ vo.amount }"/>원</td>
			<td>
				<input type="button" value="삭제" style="border:1 solid black;cursor:hand"
					   onclick="cart_delete('${ vo.c_idx }');">
			</td>
		</tr>
	  </c:forEach>

	  <c:if test="${ empty list }">
		<tr>
			<td colspan="6" align="center">
				<b>장바구니가 비었습니다.</b>
			</td>
		</tr>

		<tr>
			<td colspan="5" align="right">
				총 결제액: 
			</td>
			<td><fmt:formatNumber value="${ total_amount }"/>원</td>
		</tr>
	  </c:if>
	</table>
</form>
</body>
</html>






