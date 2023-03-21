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
       width: 400px;
       margin: auto;
       margin-top: 100px;
   }
   
   th{
       text-align: center;
   }
</style>  

<script type="text/javascript">
  
  //var regular_jumsu = /(^[1]{1}[0]{2}$|^[0-9]{1,2}$)/;  //100 or 0~99
  var regular_jumsu = /(100|^[0-9]{1,2}$)/;  //100 or 0~99

  
  function send(f){
	  
	  var name = f.name.value.trim();
	  var kor  = f.kor.value;
	  var eng  = f.eng.value;
	  var mat  = f.mat.value;
	  
	  //입력값에 대한 유효성 체크..
	  if(name==''){
		 alert('이름을 입력하세요');
		 f.name.value='';
		 f.name.focus();
		 return; 
	  }
	  
	  //점수에 대한 정규식 체크(0~100사이)
	  if(regular_jumsu.test(kor)==false){
		  alert('점수는 0~100사이 숫자만 입력하세요!!');
		  f.kor.value='';
		  f.kor.focus();
		  return;
	  }	  
	  
	  if(regular_jumsu.test(eng)==false){
		  alert('점수는 0~100사이 숫자만 입력하세요!!');
		  f.eng.value='';
		  f.eng.focus();
		  return;
	  }
	  
	  if(regular_jumsu.test(mat)==false){
		  alert('점수는 0~100사이 숫자만 입력하세요!!');
		  f.mat.value='';
		  f.mat.focus();
		  return;
	  }	
	  
	  //수정확인
	  if(confirm("정말 수정하시겠습니까?")==false)return;
	  
	
	  f.action = "modify.do";//SungModifyAction
	  f.submit();//전송
  }

</script>


</head>
<body>

<form>
  <div id="box">
    
    <div class="panel panel-primary">
      <div class="panel-heading"><h4>성적수정하기</h4></div>
      <div class="panel-body">
         <table class="table">
             <tr>
                 <th width="40%">번호</th>
                 <td><input name="idx"  value="${ vo.idx }"  readonly="readonly"></td>
             <tr>  
             <tr>
                 <th width="40%">이름</th>
                 <td><input name="name"  value="${ vo.name }"></td>
             <tr>         
             <tr>
                 <th>국어</th>
                 <td><input name="kor"  value="${ vo.kor }"></td>
             <tr>         
             <tr>
                 <th>영어</th>
                 <td><input name="eng"  value="${ vo.eng }"></td>
             <tr>         
             <tr>
                 <th>수학</th>
                 <td><input name="mat"  value="${ vo.mat }"></td>
             <tr> 
             
             <tr>
                 <td colspan="2" align="center">
                    
                     <input class="btn  btn-primary" type="button"  value="수정하기"
                            onclick="send(this.form);"  >
                     <input class="btn  btn-success" type="button"  value="목록보기"
                            onclick="location.href='list.do'" >
                 
                 </td>
             </tr>
                     
         </table>
      </div>
    </div>

  </div>
</form>  
  
</body>
</html>