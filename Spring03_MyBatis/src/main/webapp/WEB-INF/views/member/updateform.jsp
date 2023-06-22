<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>회원정보 수정폼</h1>
	<form action="update" method="post">
		<label for="num">번호</label> 
			<input type="text" id="num" value="${dto.num}" name="num" readonly><br>
			
		<label for="name">이름</label> 
			<input type="text" id="name" name="name" value="${dto.name}"><br>
			
		<label for="addr">주소</label> 
			<input type="text" id="addr" name="addr" value="${dto.addr}"><br>
			
		<button type="submit">수정</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>