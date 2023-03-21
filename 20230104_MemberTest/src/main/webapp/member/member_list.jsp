<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap 3.x 사용설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- sweetalert2 사용설정 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style type="text/css">
	@font-face {
	    font-family: 'Humanbumsuk';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-2@1.0/Humanbumsuk.woff2') format('woff2');
	    font-weight: normal;
	    font-style: normal;
	}
	
	#box{
	    width: 1000px;
	    margin: auto;
	    margin-top: 20px; 
	}
	
	#title{
	    text-align: center;
	    font-size : 28px;   /* px(가변) vs pt(고정)*/
	    font-family: 'Humanbumsuk';
	    color: aqua;
	    
	    text-shadow: 1px 1px 1px gray;
	}

</style>
<script type="text/javascript">

	function del(f){
		
		Swal.fire({
		  title: '정말 삭제하시겠습니까?',
		  text: "현재 선택된 게시물이 삭제됩니다",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '삭제',
		  cancelButtonText: '취소',
		}).then((result) => {
		  if (result.isConfirmed) {//확인버튼 클릭시
			  
			  location.href="delete.do?idx=" + ${user.mem_idx}
			  
			  //console.log(idx);
		  }
		});
	}

</script>
</head>
<body>
<form>
  <div id="box">
    <h1 id="title">::: 회원목록 :::</h1>
    
       <!-- 로그인 메뉴 -->
       <div style="text-align: right; margin-top: 5px; margin-bottom: 5px;">
       
          <!-- 로그인이 안되었을 경우 -->
          <c:if test="${ empty sessionScope.user || user.mem_id ne vo.mem_id }">
	         <input class="btn btn-primary" type="button" value="로그인" 
                    onclick="location.href='login_form.do'">
          </c:if>
          
          <!-- 로그인이 되었을경우 -->
          <c:if test="${ not empty sessionScope.user || user.mem_id eq vo.mem_id }}">
              <b>${ user.mem_id }</b>님 환영합니다
              <input class="btn btn-primary" type="button"  value="로그아웃"
              		 onclick="location.href='logout.do'">
          </c:if>
                   
          <input  class="btn btn-primary" type="button" value="회원가입" 
                  onclick="location.href='insert_form.do'">
       </div> 
	
	<table class = "table">
	  <tr>
	    <th>번호</th>
	    <th>이름</th>
	    <th>아이디</th>
	    <th>비밀번호</th>
	    <th>우편번호</th>
	    <th>주소</th>
	    <th>등급</th>
	    <th>가입일자</th>
	    <th>편집</th>
	  </tr>
	  
	  <!-- 가입된 회원정보가 없는 경우 -->
      <c:if test="${ empty list }">
           <tr>
              <td colspan="9" align="center">
                 <font color="red">가입된 회원정보가 없습니다</font>
              </td>
           </tr>
       </c:if>
       
       <!-- 회원목록출력 -->
       <!-- for(MemberVo vo : list) -->
       <c:forEach var="vo" items="${ list }">
         <tr>
           <td id="mem_idx">${ vo.mem_idx }</td>
           <td>${ vo.mem_name }</td>
           <td>${ vo.mem_id }</td>
           <td>${ vo.mem_pwd_hide }</td>
           <td>${ vo.mem_zipcode }</td>
           <td>${ vo.mem_address }</td>
           <td>${ vo.mem_grade }</td>
           <td>${ fn:substring(vo.mem_regdate,0,10) }</td>
           <td>
             <!-- 편집메뉴의 활성화는 관리자 또는 본인 -->
             <c:if test="${ user.mem_grade eq '관리자' || user.mem_id eq vo.mem_id }">
	             <input class="btn btn-info btn-sm" type="button" value="수정"
	             		onclick="location.href='modify_form.do?mem_idx=${ vo.mem_idx }'">
	             <input class="btn btn-danger btn-sm" type="button" value="삭제"
	             		onclick="del(this.form);">
	         </c:if>
           </td>
         </tr>
       </c:forEach>
	</table>
  </div>
</form>
</body>
</html>