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
  
  
<style type="text/css">

@font-face {
    font-family: 'Humanbumsuk';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-2@1.0/Humanbumsuk.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

  #box{
      width: 500px;
      margin: auto;
      margin-top: 30px;
  }
  
  #title{
      text-align: center;
      font-size: 30px;
      font-weight: bold;
      font-family: 'Humanbumsuk';
      color: green;
  }
  
  #empty_message{
      text-align: center;
      color: red;
      font-weight: bold;
      font-size: 20px;
      font-family: 휴먼옛체,궁서체,굴림체;
  }
  
  .content{
  
	  /* min-height: 50px; */
	  padding: 5px;
  }

  .regdate{
	  
	  display: flex;
	  justify-content: flex-end;
	  margin-top: 10px;
	  padding-bottom: 10px;  
	  
  }
  

  
</style>

<script type="text/javascript">

  function del(f){
	  
	  var idx = f.idx.value;
	  var pwd = f.pwd.value;//원래비번
	  
	  var c_pwd = f.c_pwd.value.trim();//확인비번
	  
	  if(pwd != c_pwd){
		  alert('비밀번호가 일치하지 않습니다');
		  f.c_pwd.value='';
		  f.c_pwd.focus();
		  return;
	  }
	  
	  //삭제확인
	  if(confirm("정말 삭제하시겠습니까?")==false)return;
	  
	  //f.action = "delete.do"; //VisitDeleteAction
	  //f.submit();
	  
	  location.href="delete.do?idx=" + idx;
	  
  }
  
  function edit(f){
	  
	  var idx = f.idx.value;
	  var pwd = f.pwd.value;//원래비번
	  
	  var c_pwd = f.c_pwd.value.trim();//확인비번
	  
	  if(pwd != c_pwd){
		  alert('비밀번호가 일치하지 않습니다');
		  f.c_pwd.value='';
		  f.c_pwd.focus();
		  return;
	  }
	  
  
	  //f.action = "delete.do"; //VisitDeleteAction
	  //f.submit();
	  
	  location.href="modify_form.do?idx=" + idx;
	  
  }
  


</script>
</head>
<body>
  <div id="box"> 
     <h1 id="title">::::방 명 록::::</h1>
     <div style="text-align: right; margin-bottom: 10px;">
         <input class="btn btn-primary" type="button"  value="글쓰기"
         		onclick="location.href='insert_form.do'">
     </div> 
     
     <!-- data  -->
     <div>
        <!-- 데이터가 없는경우 -->
        <c:if test="${ empty list }">
          <div id="empty_message">게시물이 없습니다</div>
        </c:if>
        
        <!-- 데이터가 있는경우 -->
        <!--  for(VisitVo vo : list) 동일 -->
        <c:forEach var="vo"  items="${ list }">
	        <form>
	            <input type="hidden"  name="pwd"  value="${ vo.pwd }">
	            <input type="hidden"  name="idx"  value="${ vo.idx }">
	            
	            <div class="panel panel-primary">
				      <div class="panel-heading"><h4><b>${ vo.name }</b>님의 글</h4></div>
				      <div class="panel-body">
				          
				          <div class="content">${ vo.content }</div>
				          <div class="regdate">작성일자 : ${ fn:substring(vo.modifydate,0,16) }(${ vo.ip })</div>
				          <div class="pwd">
				             비밀번호(${ vo.pwd }):<input type="password"  name="c_pwd">
				                      <input class="btn  btn-info"     type="button"  value="수정"
				                             onclick="edit(this.form)" >  
				                      <input class="btn  btn-danger"   type="button"  value="삭제" 
				                             onclick="del(this.form);">  
			          </div>			      
			      </div>
			</div>
          </form>
        </c:forEach>
             
     </div>     
  </div>
</body>
</html>





