<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
   
   //초기화 이벤트
   $(document).ready(function(){
	   
	   //제품번호 입력창에서 키가눌렸다 떼면
	   $("#p_num").keyup(function(){
		   
		   //비활성화
		   $("#btn_confirm").attr("disabled", true);
		   
		   var p_num = $(this).val();
		   //console.log(p_num);
		   if(p_num.length<3){
			   
			   $("#num_msg").html("제품번호는 3자리 이상이어야 합니다")
			               .css("color","red");
			   return;
		   }
		   
		   $("#num_msg").html("");
		   
		   //Ajax로 아이디 사용유무 의뢰
		   $.ajax({
			   url		: "check_num.do",      //ProductCheckAction
			   data		: {"p_num": p_num}, //check_num.do?p_num=
			   dataType	: "json",
			   success	: function(result_data){
				   //result_data = {"result": true} or {"result": false}
				   if(result_data.result==true){
					   
					   $("#num_msg").html("사용가능한 제품번호입니다")
		                           .css("color","blue");
					   
					   //가입버튼 활성화
					   // <input    disabled="disabled">
					  // $("#btn_join").prop("disabled", false);
					   $("#btn_confirm").attr("disabled", false);
					   
				   }else{
					   
					   $("#num_msg").html("이미 사용중인 제품번호입니다")
                                   .css("color","red");
					  
					   
				   }
			   },
			   error	: function(err){
				   
				   alert(err.responseText);
				   
			   }
		   });
		   
		   
		   
	   });
	   
   });
</script>

<script type="text/javascript">

    var regular_number = /^[0-9]{1,}$/;
    

	function send(f) {

		var category 	= f.category.value;
		var p_num 		= f.p_num.value;
		var p_name 		= f.p_name.value;
		var p_company 	= f.p_company.value;
		var p_price 		= f.p_price.value;
		var p_saleprice 	= f.p_saleprice.value;
		var p_content 	= f.p_content.value;
		var p_photo_s 	= f.p_photo_s.value;
		var p_photo_l 	= f.p_photo_l.value;
		
		//가격(숫자만입력)
		if(regular_number.test(p_price)==false){
		alert("가격은 숫자만 입력하세요!!");
		f.p_price.value = '';
		f.p_price.focus();
		return;
		}
		
		if(regular_number.test(p_saleprice)==false){
		alert("가격은 숫자만 입력하세요!!");
		f.p_saleprice.value = '';
		f.p_saleprice.focus();
		return;
		}
		  
		//각각의 항목별로 입력체크...
		if(category==''){
		 alert("등록할 상품의 카테고리를 선택하세요");
		 return;
		}
		
		//제품번호는 Ajax이용해서 체크해볼것
		if(p_num==''){
		 alert("제품번호를 입력하세요!!");
		 f.p_num.value='';
		 f.p_num.focus();
		 return;
		}
	   
	   if(p_name==''){
		   alert("제품명 입력하세요!!");
		   f.p_name.value='';
		   f.p_name.focus();
		   return;
	   }
	   
	   if(p_company==''){
		   alert("제조사명 입력하세요!!");
		   f.p_company.value='';
		   f.p_company.focus();
		   return;
	   }
	   
	   if(p_content==''){
		   alert("제품 설명을 입력하세요!!");
		   f.p_content.value='';
		   f.p_content.focus();
		   return;
	   }
	   
	   if(p_photo_s==''){
		   alert("작은 사진을 선택(첨부)하세요");
		   return;
	   }
	   
	   if(p_photo_l==''){
		   alert("큰 사진을 선택(첨부)하세요");
		   return;
	   }
	   
	   //f.action="insert.do"; //ProductInsertAction
	   f.submit();
	   
	}
</script>
</head>
<body>

<!-- title -->
<%-- <jsp:include page="index.jsp"/> --%>
<%@include file="index.jsp" %>


<form  method="POST" action="insert.do"  enctype="multipart/form-data"> 

	<table align="center" width="600" border="1" 
	    style="border-collapse:collapse;font-size:8pt" bordercolor="navy"
	    cellpadding="2" cellspacing="0">
	    <tr>
	        <td>제품Category</td>
	        <td><select name="category">
	        		<option value="">카테고리 선택</option>
	        		<c:forEach var="category"  items="${ category_list }">
	        		    <option value="${ category.category_code }">${ category.category_name }</option>
	        		</c:forEach>
	        		<!-- <option value="com001">컴퓨터</option>
	        		<option value="ele002">가전제품</option>
	        		<option value="sp003">스포츠</option> -->
	        </select></td>
	    </tr>
	    <tr>
	        <td>제품번호</td>
	        <td><input name="p_num" id="p_num" type="text" ><span id="num_msg"></span></td>
	    </tr>
	    <tr>
	        <td>제품이름</td>
	        <td><input name="p_name" type="text" ></td>
	    </tr>
	    <tr>
	        <td>제품 제조사</td>
	        <td><input name="p_company" type="text" ></td>
	    </tr>
	    <tr>
	        <td>제품가격</td>
	        <td><input name="p_price" type="text" ></td>
	    </tr>
	    <tr>
	        <td>제품할인가격</td>
	        <td><input name="p_saleprice" type="text" ></td>
	    </tr>    
	    <tr>
	        <td>제품설명</td>
	        <td><TEXTAREA name="p_content" rows="5" cols="50"></TEXTAREA></td>
	    </tr>
	    <tr>
	        <td>제품사진(작은사진)</td>
	        <td><input type="file" name="p_photo_s">
	    </tr>
	    <tr>
	        <td>제품사진(큰사진)</td>
	        <td><input type="file" name="p_photo_l">
	    </tr>
	    <tr>
	        <td colspan="2" align="center">
	            <input type="button" value="등록" id="btn_confirm" 
	                   onclick="send(this.form);" >
	            <input type="reset" value="Clear" >
	        </td>
	    </tr>    
	</table>
</form>
</body>
</html>