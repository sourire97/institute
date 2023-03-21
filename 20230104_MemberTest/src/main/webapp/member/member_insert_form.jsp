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

<!-- sweetalert2 사용설정 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- 우편번호 검색 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

  
<style type="text/css">
  #box{
      width: 600px;
      margin: auto;
      margin-top: 50px;
  }
  
  #id_msg{
     display: inline-block;
     width: 250px;
     margin-left: 20px;
  }
</style>  

<script type="text/javascript">

  //초기화 이벤트
  $(document).ready(function(){
	  
	 //아이디입력창에서 키가 눌렷다 떼지면
	 $("#mem_id").keyup(function(){
		
		 $("#btn_join").attr("disabled", true);
		
		var mem_id = $(this).val();
		if(mem_id.length<3){
		   
		   $("#id_msg").html("아이디는 3자리 이상이어야 합니다.").css("color", "red");
		   return;
	   }
	   
	   $("#id_msg").html("");
	   
	   //Ajax로 아이디 사용유무 확인
	   $.ajax({
		   
		   url	:	"check_id.do", //MemberCheckIdAction
		   data	:	{"mem_id" : mem_id}, //check_id.do?mem_id=one
		   dataType: "json",
		   success : function(result_data){
			   if(result_data.result == true){
				   
				   //안내메세지
				   $("#id_msg").html("사용가능한 아이디입니다.").css("color", "blue");
				   
				   //가입버튼 활성화
				   //$("#btn_join").attr("disabled", false);
				   $("#btn_join").prop("disabled", false);
				   
			   }else{
				   
				   $("#id_msg").html("이미 사용중인 아이디입니다.").css("color", "red");
				   
			   }
			   
		   },
		   error   : function(err){
			   
			   alert(err.responseText);
			   
		   }
	   });
	   
	   
	 });
	  
  });

</script>

<script type="text/javascript">

	function find_address(){
		
	    new daum.Postcode({
	        oncomplete: function(data) {
	            //data = {"zonecode":12345, "address":"서울시 관악구",}
	            
	            $("#mem_zipcode").val(data.zonecode);
	            $("#mem_address").val(data.address);
	        }
	    }).open();
	
	}
   
	function send(f){
		  
		  var mem_name		= f.mem_name.value.trim();
		  var mem_id		= f.mem_id.value.trim();
		  var mem_pwd		= f.mem_pwd.value.trim();
		  var mem_zipcode	= f.mem_zipcode.value.trim();
		  var mem_address	= f.mem_address.value.trim();
		  
		   if(mem_name == ''){
			   
				  Swal.fire({ 
			          title:'이름을 입력하세요',
			          html:'이름 누락',
			          icon:'info',
			          returnFocus:false
			        }).then(function(){
				  //확인버튼 누른후 처리
		          f.mem_name.value='';
				  f.mem_name.focus();
			  });
			  
			  return;
		  }
		   
		   if(mem_id == ''){
			   
				  Swal.fire({ 
			          title:'아이디를 입력하세요',
			          html:'아이디 누락',
			          icon:'info',
			          returnFocus:false
			        }).then(function(){
				  //확인버튼 누른후 처리
		          f.mem_id.value='';
				  f.mem_id.focus();
			  });
			  
			  return;
		  }
		   
		   if(mem_pwd == ''){
			   
				  Swal.fire({ 
			          title:'비밀번호를 입력하세요',
			          html:'비밀번호 누락',
			          icon:'info',
			          returnFocus:false
			        }).then(function(){
				  //확인버튼 누른후 처리
		          f.mem_pwd.value='';
				  f.mem_pwd.focus();
			  });
			  
			  return;
		  }
		   
		   if(mem_zipcode == ''){
			   
				  Swal.fire({ 
			          title:'우편번호를 입력하세요',
			          html:'우편번호 누락',
			          icon:'info',
			          returnFocus:false
			        }).then(function(){
				  //확인버튼 누른후 처리
		          f.mem_zipcode.value='';
				  f.mem_zipcode.focus();
			  });
			  
			  return;
		  }
		   
		   if(mem_address == ''){
			   
				  Swal.fire({ 
			          title:'주소를 입력하세요',
			          html:'주소 누락',
			          icon:'info',
			          returnFocus:false
			        }).then(function(){
				  //확인버튼 누른후 처리
		          f.mem_address.value='';
				  f.mem_address.focus();
			  });
			  
			  return;
		  }
		  
		   //입력확인
		   Swal.fire({
			   title: '정보를 올바르게 입력했습니까?',
			   icon: 'question',
			   showCancelButton: true,
			   confirmButtonColor: '#3085d6',
			   cancelButtonColor: '#d33',
			   confirmButtonText: '네',
			   cancelButtonText: '아니요'
			 }).then((result) => {
			   if (result.isConfirmed) {

			     Swal.fire(
			       '입력됨!',
			     ).then((result) => {
			    	if(result.isConfirmed){
			    		
			  		     f.action="insert.do";//  MemberInsertAction
					     f.submit();
			    	}
			     })
			   }
			 })
	   }

</script>

</head>
<body>

<form>
  <div id="box">
    <div class="panel panel-primary">
      <div class="panel-heading"><h4>회원가입</h4></div>
      <div class="panel-body">
        <table class="table">
          <tr>
            <th>이름</th>
            <td><input name="mem_name"></td>
          </tr>
          <tr>
            <th>아이디</th>
            <td><input name="mem_id" id="mem_id"><span id="id_msg"></span></td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td><input type="password" name="mem_pwd"></td>
          </tr>
          <tr>
            <th>우편번호</th>
            <td>
              <input name="mem_zipcode" id="mem_zipcode">
              <input type="button" value="주소검색" onclick="find_address();">
            </td>
          </tr>
          <tr>
            <th>주소</th>
            <td><input name="mem_address" id="mem_address"></td>
          </tr>                  
          <tr>
            <td colspan="2" align="center">
              <input class="btn btn-primary" type="button"  value="회원가입" id="btn_join"
              		 onclick="send(this.form);">
              <input class="btn btn-success" type="button"  value="목록보기"
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