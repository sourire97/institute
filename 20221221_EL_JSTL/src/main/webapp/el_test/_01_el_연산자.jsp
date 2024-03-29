<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!-- HTML주석 -->
<%-- JSP 주석 --%>

<%-- 
     EL(Expression Language):표현언어
     
     형식) ${  값(상수 or 변수)   }

--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
  <h4>산술연산자</h4>
<hr>
\${ 3 + 1 } => ${ 3 + 1 } <br>
\${ 3 - 1 } => ${ 3 - 1 } <br>
\${ 3 * 1 } => ${ 3 * 1 } <br>
\${ 10 / 5 } => ${ 10 / 5 }  <%-- or ${ 10 div 5 } --%><br>
\${ 10 % 3 } => ${ 10 % 3 }  <%-- or ${ 10 mod 3 } --%><br>

<hr>
  <h4>관계연산자</h4>
<hr>
\${ 3 >  2 } => ${ 3 >  2 } or ${ 3 gt 2 }<br> <!-- greater than  --> 
\${ 3 >= 2 } => ${ 3 >= 2 } or ${ 3 ge 2 }<br> <!-- greater equal -->
\${ 3 <  2 } => ${ 3 <  2 } or ${ 3 lt 2 }<br> <!-- less than  --> 
\${ 3 <= 2 } => ${ 3 <= 2 } or ${ 3 le 2 }<br> <!-- less equal -->
\${ 3 == 2 } => ${ 3 == 2 } or ${ 3 eq 2 }<br> <!-- equal      -->
\${ 3 != 2 } => ${ 3 != 2 }<%--  or ${ 3 ne 2 } --%><br> <!-- not equal  -->


<hr>
  <h4>논리연산자</h4>
<hr>
\${ true && true  } => ${ true && true  } or ${ true and true  }<br> 
\${ true && false } => ${ true && false } or ${ true and false }<br> 

\${ true || true  } => ${ true || true  } or ${ true or true  }<br> 
\${ true || false } => ${ true || false } or ${ true or false }<br> 
\${ false || false } => ${ false || false } or ${ false or false }<br> 

\${ !true } => ${ !true } or ${ not true } <br>

<hr>
  <h4>기타연산자(3항/empty)</h4>
<hr>
<!-- 
    el_test/_01_el_연산자.jsp
    el_test/_01_el_연산자.jsp?msg=
    el_test/_01_el_연산자.jsp?msg=안녕하세요
    3항연산자
    (조건) ? 참값 : 거짓값
 -->
파라메터 msg : ${ (empty param.msg) ? '메시지 없음' : param.msg  } <br>



</body>
</html>