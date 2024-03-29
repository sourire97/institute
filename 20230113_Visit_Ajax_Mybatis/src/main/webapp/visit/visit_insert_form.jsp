<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
  #box{
      width: 500px;
      margin: auto;
      margin-top: 30px;
  }
  
  textarea {
	  width: 100%; 
	  resize: none;
  }
  
</style>

<script type="text/javascript">
function send(f){
	  
	  var name    = f.name.value.trim();
	  var content = f.content.value.trim();
	  var pwd     = f.pwd.value.trim();
	  
	  if(name==''){
		  alert('이름을 입력하세요');
		  f.name.value='';
		  f.name.focus();
		  return;
	  }
	  
	  if(content==''){
		  alert('내용을 입력하세요');
		  f.content.value='';
		  f.content.focus();
		  return;
	  }
	  
	  if(pwd==''){
		  alert('비밀번호를 입력하세요');
		  f.pwd.value='';
		  f.pwd.focus();
		  return;
	  }
	  	  
	  
	  f.action = "insert.do";// VisitInsertAction
	  f.submit();//전송
	  
	  
}
</script>

</head>
<body>
<form>
  <div id="box">
      <div class="panel panel-primary">
	      <div class="panel-heading"><h4>방명록 글쓰기</h4></div>
	      <div class="panel-body">
	          <table class="table">
	              <tr>
	                  <th>작성자</th>
	                  <td><input name="name"></td>
	              </tr>
	              <tr>
	                  <th>내용</th>
	                  <td><textarea  name="content" rows="5" cols="50"></textarea></td>
	              </tr>
	              <tr>
	                  <th>비밀번호</th>
	                  <td><input type="password" name="pwd"></td>
	              </tr>
	              
	              <tr>
	                  <td colspan="2" align="center">
	                      <input class="btn  btn-primary" type="button"  value="글올리기" 
	                             onclick="send(this.form);">
	                      <input class="btn  btn-success" type="button"  value="목록보기" 
	                             onclick="location.href='list.do'">
	                  </td>
	              </tr>
	              
	          </table>
	      </div>
	  </div>
  </div>
</form>  
</body>
</html>